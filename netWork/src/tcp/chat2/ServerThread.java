package tcp.chat2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @File Name: tcp.chat2
 * @Author: WQL //作者及
 * @Date: 2019/7/29 15:36//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class ServerThread implements Runnable
{

    public Socket socket;

    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        try
        {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true)
            {
                String msg = br.readLine();
                System.out.println(msg);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}