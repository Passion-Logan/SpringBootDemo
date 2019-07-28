package tcp.chat;

import java.io.*;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:09//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Client
{
    public static void main(String[] args) throws IOException
    {
        Socket client = new Socket("localhost", 9999);

        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
