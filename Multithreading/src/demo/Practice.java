package demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @File Name: demo
 * @Author: WQL
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

//    ReentrantLock rt = new ReentrantLock();

    /**
     * 1 可使用 synchronized 锁 run方法
     * 2 可使用 synchronized 锁 代码块
     * 3 可使用 ReentrantLock 加锁后必须显示地进行释放锁，释放锁必须在finally中执行，否则会造成死锁
     *   synchronized在遇到异常后会自动释放，ReentrantLock不会
     *
     */
    @Override
    synchronized public void run()
    {
        final ReentrantLock rt = new ReentrantLock();

        try
        {
            rt.lock();
//        synchronized (this)
//        {
            if (store > 0)
            {
                store--;
                System.out.println("线程 " + Thread.currentThread().getName() + "买到了， 剩余库存 : " + store);
            }
//        }
        } finally
        {
            rt.unlock();
        }
    }
}