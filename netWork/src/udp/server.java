package udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @File Name: udp
 * @Author: WQL //作者及
 * @Date: 2019/7/28 21:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class server
{

    public static void main(String[] args) throws IOException
    {
        DatagramSocket server = new DatagramSocket(8888);
        byte[] container = new byte[1024];
        DatagramPacket packet = new DatagramPacket(container, container.length);

        server.receive(packet);

        byte[] data = packet.getData();
        int len = packet.getLength();

        System.out.println(new String(data, 0, len));
        server.close();
    }
}
