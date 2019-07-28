package tcp;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @File Name: tcp
 * @Author: WQL //作者及
 * @Date: 2019/7/28 21:59//完成日期
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
        Socket client = new Socket("localhost", 8888);

        BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));

        DataInputStream dis = new DataInputStream(client.getInputStream());

        String echo = dis.readUTF();

        System.out.println(echo);
    }
}
