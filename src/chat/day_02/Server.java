package chat.day_02;

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
        server = new ServerSocket(9999);
        while (true){
            //接收客户端连接    阻塞式
            Socket socket = server.accept();
            //接收数据  输入流
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //发送数据  输出流
            writer = new PrintWriter(socket.getOutputStream());
            while (true){
                String msg = reader.readLine();
                writer.println("服务器--->" + msg);
                System.out.println(msg);
                writer.flush();
            }
        }


    }
    public static void main(String[] args) throws Exception {
        Server server = new Server();
        server.go();
    }
}
