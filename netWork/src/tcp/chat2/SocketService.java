package tcp.chat2;

import jdk.net.Sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File Name: tcp.chat2
 * @Author: WQL //作者及
 * @Date: 2019/7/29 15:34//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class SocketService
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket serverSocket = new ServerSocket(9999);

        System.out.println("服务器启动成功");

        while (true)
        {
            Socket socket = serverSocket.accept();

            System.out.println("上线通知： " + socket.getInetAddress() + ":" +socket.getPort());
            new Thread(new ServerThread(socket)).start();
        }
    }
}