package udp;

import java.io.IOException;
import java.net.*;

/**
 * @File Name: udp
 * @Author: WQL //作者及
 * @Date: 2019/7/28 21:29//完成日期
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
        DatagramSocket client = new DatagramSocket(6666);

        String msg = "测试发送数据";
        byte[] data = msg.getBytes();

        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));

        client.send(packet);
        client.close();
    }
}
