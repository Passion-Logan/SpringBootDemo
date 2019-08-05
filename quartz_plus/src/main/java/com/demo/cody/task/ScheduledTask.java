package com.demo.cody.task;

import java.util.concurrent.ScheduledFuture;

/**
 * @File Name: com.demo.cody.task
 * @Author: WQL //作者及
 * @Date: 2019/8/5 17:26//完成日期
 * @Description: // 定时任务控制类
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public final class ScheduledTask
{

    public volatile ScheduledFuture<?> future;

    /**
     * 取消定时任务
     */
    public void cancel()
    {
        ScheduledFuture<?> future = this.future;

        if (future != null)
        {
            future.cancel(true);
        }
    }
}
