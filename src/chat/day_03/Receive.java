package chat.day_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/*
接收数据线程
 */

public class Receive implements Runnable {
    private BufferedReader reader;          //输入流  接收数据
    //使用带参构造器初始化
    public Receive(Socket client){
        String msg = null;
        try {
            //接收数据
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            msg = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(msg);
    }
    /*
    接收数据
     */
    public String receive(){
        String message = "";
        try {
            message = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }
    @Override
    public void run() {
        while (true){
            System.out.println(receive());
        }
    }
}
