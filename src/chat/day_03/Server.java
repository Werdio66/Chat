package chat.day_03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

/*
1.创建服务器 指定端口
2.接收客户端连接
3.发送数据  +  接收数据
 */
public class Server {
    ServerSocket server;            //定义Socket
    private ArrayList<Object> list = new ArrayList<>();     //使用集合存储消息

    public static void main(String[] args) throws Exception {
        new Server().go();
    }
    public void go() throws Exception {
        //建立ServerSocket连接
        server = new ServerSocket(9999);
        //接收客户端连接    阻塞式
        Socket socket = server.accept();
        //一条路
        MyChannnel channnel = new MyChannnel(socket);
        //启动线程
        new Thread(channnel).start();
    }

    /*
    一个客户一条路
     */
    private class MyChannnel implements Runnable{
        private BufferedReader reader;          //输入流  接收数据
        private PrintWriter writer;             //输出流  发送数据

        //使用带参构造器初始化流
        public MyChannnel(Socket socket){

            try {
                //接收数据  输入流
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                //发送数据  输出流
                writer = new PrintWriter(socket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //接收数据
        private String receive(){
            String message = null;
            try {
                message = reader.readLine();
                list.add(message);
                System.out.println(message);
            } catch (IOException e) {
                e.printStackTrace();
                list.remove(this);      //移除自身
            }
            return message;
        }
//        //发送数据
//        private void send(String message){
//            if((null != message) || (message.equals("")))
//                return;
//            writer.println("服务器--->" + message);
//            writer.flush();
//        }
        //发给其他客户端
        private void sendOthers(String message) {

            //拿到list迭代器对象
           Iterator it = list.iterator();
           while (it.hasNext()){
               //自己不接受自己发的消息
               if(message == it.next())
                   continue;
               try{
                   writer.println("服务器--->" + it.next());
                   writer.flush();
               }catch (Exception e){
                   e.printStackTrace();
               }

           }
        }


        @Override
        public void run() {
            while (true){
                sendOthers(receive());
            }
        }
    }
}
