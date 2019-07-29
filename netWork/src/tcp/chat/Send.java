package tcp.chat;

import java.io.*;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:20//完成日期
 * @Description: // 发送数据线程
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Send implements Runnable
{
    private BufferedReader br;
    private DataOutputStream dos;
    private boolean isRunning = true;

    public Send()
    {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket)
    {
        this();
        try
        {
            dos = new DataOutputStream(socket.getOutputStream());
        }
        catch (IOException e)
        {
            isRunning = false;
            CloseUtil.closeAll(dos, br);
        }
    }

    /**
     * 从控制台接收数据
     * @return
     */
    private String getMsgFromConsole()
    {
        try
        {
            return br.readLine();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 从控制台接收信息后 发送出去
     */
    public void send()
    {
        String msg = getMsgFromConsole();

        if (null != msg && !msg.equals(""))
        {
            try
            {
                dos.writeUTF(msg);
                dos.flush();
            }
            catch (IOException e)
            {
                isRunning = false;
                CloseUtil.closeAll(dos, br);
            }
        }
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            send();
        }
    }
}
