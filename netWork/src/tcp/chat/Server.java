package tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:07//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Server
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务器启动成功");
        Socket socket = server.accept();

        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        while (true)
        {
            String msg = dis.readUTF();
            System.out.println("接收的： " + msg);

            dos.writeUTF("服务器----> " + msg);
            dos.flush();
        }
    }
}
