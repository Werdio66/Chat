package chat.day_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
发送数据线程
 */
public class Send implements Runnable {
    private PrintWriter writer;             //输出流  发送数据
    private BufferedReader console;         //控制台输入流
    //初始化
    public Send(Socket client){
        //控制台输入数据
        console = new BufferedReader(new InputStreamReader(System.in));
        try {
            //发送数据
            writer = new PrintWriter(client.getOutputStream());
            writer.println(console.readLine());
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //从控制台接收数据
    private String getMsgFromConole(){
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    /*
    发送数据
     */
    public void send(){
        //接收控制台输入数据
        String message = getMsgFromConole();
        if((null != message) && !(message.equals(""))){
            //发送数据
            writer.println(message);
            writer.flush();
        }
    }

    @Override
    public void run() {
        while (true){
            send();
        }
    }
}
