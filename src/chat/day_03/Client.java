
package chat.day_03;

import java.net.Socket;

/*
1.创建客户端
2.建立Socket连接
3.接收数据  +  发送数据
 */
@SuppressWarnings("all")
public class Client {
    Socket client;                  //定义Socket

    public void go() throws Exception {
        //建立Socket连接
        client = new Socket("localhost",9999);

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
    public static void main(String[] args) throws Exception {
        new Client().go();
    }
}