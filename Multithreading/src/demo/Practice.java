package demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @File Name: demo
 * @Author: WQL //作者及
 * @Date: 2019/6/27 16:13//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class Practice
{
    public static void main(String[] args)
    {
        Tickt tickt = new Tickt();

        Thread thread1 = new Thread(tickt);
        Thread thread2 = new Thread(tickt);
        Thread thread3 = new Thread(tickt);
        Thread thread4 = new Thread(tickt);
        Thread thread5 = new Thread(tickt);
        Thread thread6 = new Thread(tickt);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        thread6.start();
    }
}

class Tickt implements Runnable{
    private static Integer store = 3;

    ReentrantLock rt = new ReentrantLock();

    @Override
    public void run()
    {

        try
        {
            rt.lock();
            if (store > 0)
            {
                store--;
                System.out.println("线程 " + Thread.currentThread().getName() + "买到了， 剩余库存 : " + store);
            }
        } finally
        {
            rt.unlock();
        }
    }
}