package chat.day_01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
1.创建服务器 指定端口
2.接收客户端连接
3.发送数据  +  接收数据
 */
public class Server {
    ServerSocket server;            //定义Socket
    BufferedReader reader;          //输入流  接收数据
    PrintWriter writer;             //输出流  发送数据

    public void go() throws Exception {
        //建立ServerSocket连接
        server = new ServerSocket(7777);


        while (true){
            //接收客户端连接    阻塞式
            Socket socket = server.accept();
            //接收数据  输入流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg = reader.readLine();
            //发送数据  输出流
            writer = new PrintWriter(socket.getOutputStream());
            writer.println("服务器--->" + msg);
            writer.flush();
            System.out.println(msg);
        }

    }
    public static void main(String[] args) throws Exception {
        new Server().go();
    }
}
