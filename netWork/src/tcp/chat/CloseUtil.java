package tcp.chat;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.Closeable;
import java.io.IOException;

/**
 * @File Name: tcp.chat
 * @Author: WQL //作者及
 * @Date: 2019/7/28 23:32//完成日期
 * @Description: // 关闭流的工具类
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class CloseUtil
{
    public static void closeAll(Closeable... io)
    {
        for (Closeable temp : io)
        {
            if (null != temp)
            {
                try
                {
                    temp.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
