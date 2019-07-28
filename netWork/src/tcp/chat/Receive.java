package tcp.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:21//完成日期
 * @Description: // 接收数据线程
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Receive implements Runnable
{
    private DataInputStream dis;
    private boolean isRunning = true;

    public Receive(){};

    public Receive(Socket socket)
    {
        try
        {
            dis = new DataInputStream(socket.getInputStream());
        }
        catch (IOException e)
        {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
    }

    /**
     * 接收数据
     * @return
     */
    public String receive()
    {
        String msg = "";
        try
        {
            msg = dis.readUTF();
        }
        catch (IOException e)
        {
            isRunning = false;
            CloseUtil.closeAll(dis);
        }
        return msg;
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            System.out.println(receive());
        }
    }
}
