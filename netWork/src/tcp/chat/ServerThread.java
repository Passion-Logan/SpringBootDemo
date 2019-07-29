package tcp.chat;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/29 15:15//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class ServerThread implements Runnable
{

    private Socket socket;
    private BufferedReader br;
    private boolean isRunning = true;

    public ServerThread(Socket socket)
    {
        this.socket = socket;
    }

    public ServerThread(BufferedReader br) throws IOException
    {
        this.br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public String getMsg(BufferedReader br)
    {
        String msg;

        try
        {
            msg = br.readLine();

            return msg;
        } catch (IOException e)
        {
            isRunning = false;
            CloseUtil.closeAll(br);
        }
        return null;
    }

    @Override
    public void run()
    {
        while (isRunning)
        {
            System.out.println("服务端 --> " + getMsg(br));
        }
    }
}