package chat.day_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/*
1.创建客户端
2.建立Socket连接
3.接收数据  +  发送数据
 */
public class Client {
    Socket client;                  //定义Socket
    BufferedReader reader;          //输入流  接收数据
    PrintWriter writer;             //输出流  发送数据

    public void go() throws Exception {
        //建立Socket连接
        client = new Socket("localhost",7777);
        //控制台输入数据
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        while (true){

            //发送数据
            writer = new PrintWriter(client.getOutputStream());
            writer.println(console.readLine());
            writer.flush();
            //接收数据
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String msg = reader.readLine();
            System.out.println(msg);
        }

    }
    public static void main(String[] args) throws Exception {
        new Client().go();
    }
}
