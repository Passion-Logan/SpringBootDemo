package tcp.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:07//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Server
{
    private List<MyChannel> all = new ArrayList<>();

    public static void main(String[] args) throws IOException
    {
        new Server().start();
    }

    public void start() throws IOException
    {
        ServerSocket server = new ServerSocket(9999);
        System.out.println("服务器启动成功");

        while (true)
        {
            Socket socket = server.accept();

            MyChannel channel = new MyChannel(socket);
            all.add(channel);
            // 一个连接
            new Thread(channel).start();
        }
    }



    /**
     * 一个客户端 一个连接1
     * 1 输入流
     * 2 输出流
     * 3 接收数据
     * 4 发送数据
     */
    private class MyChannel implements Runnable
    {

        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;

        public MyChannel(Socket socket)
        {
            try
            {
                this.dis = new DataInputStream(socket.getInputStream());
                this.dos = new DataOutputStream(socket.getOutputStream());
            }
            catch (IOException e)
            {
                isRunning = false;
                CloseUtil.closeAll(dis, dos);
            }
        }

        /**
         * 读取数据
         *
         * @return
         */
        private String receive()
        {
            String msg = "";

            try
            {
                msg =  dis.readUTF();
            }
            catch (IOException e)
            {
                isRunning = false;
                CloseUtil.closeAll(dis);
                // 出现问题后 移除自身
                all.remove(this);
            }

            return msg;
        }

        /**
         * 发送数据
         *
         * @param msg
         */
        private void send(String msg)
        {
            if (null == msg || msg.equals(""))
            {
                return;
            }
            try
            {
                dos.writeUTF(msg);
                dos.flush();
            }
            catch (IOException e)
            {
                isRunning = false;
                CloseUtil.closeAll(dos);
                all.remove(this);
            }
        }

        /**
         * 发送给其他客户端
         */
        private void sendOthers()
        {
            String msg = receive();

            // 遍历容器
            for (MyChannel channel : all)
            {
                if (channel == this)
                {
                    continue;
                }
                // 发送给其他客户端
                channel.send(msg);
            }
        }

        @Override
        public void run()
        {
            while (isRunning)
            {
                sendOthers();
            }
        }
    }
}


