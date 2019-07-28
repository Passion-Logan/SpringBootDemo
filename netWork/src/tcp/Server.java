package tcp;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @File Name: tcp
 * @Author: WQL //作者及
 * @Date: 2019/7/28 21:58//完成日期
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
        ServerSocket server = new ServerSocket(8888);
        Socket socket = server.accept();
        System.out.println("一个客户端建立连接");

        String msg = "欢迎使用";

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();
    }
}
