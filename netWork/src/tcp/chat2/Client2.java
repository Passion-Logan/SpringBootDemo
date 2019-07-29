package tcp.chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @File Name: tcp.chat2
 * @Author: WQL //作者及
 * @Date: 2019/7/29 15:54//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Client2
{
    public static void main(String[] args) throws IOException
    {
        Socket socket = new Socket("localhost", 9999);
        System.out.println("用户二连接成功");

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(socket.getOutputStream());

        while (true)
        {
            pw.print("用户二说：" + br.readLine());
            pw.flush();
        }
    }
}