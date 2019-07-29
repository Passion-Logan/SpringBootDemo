package tcp.chat;

import java.io.IOException;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/29 23:46//完成日期
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
        Socket client = new Socket("localhost", 9999);
        System.out.println("客户端2");

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
