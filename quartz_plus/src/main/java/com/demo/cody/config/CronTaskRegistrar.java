package com.demo.cody.config;

import com.demo.cody.task.ScheduledTask;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.config.CronTask;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @File Name: com.demo.cody.schedul
 * @Author: WQL //作者及
 * @Date: 2019/8/5 17:06//完成日期
 * @Description: // 定时任务注册类,添加定时任务注册类，用来增加、删除定时任务。
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class CronTaskRegistrar implements DisposableBean
{

    private final Map<Runnable, ScheduledTask> scheduledTasks = new ConcurrentHashMap<>(16);

    @Autowired
    private TaskScheduler taskScheduler;

    public TaskScheduler getScheduler()
    {
        return this.taskScheduler;
    }

    /**
     * 新增定时任务
     *
     * @param task
     * @param cronExpression
     */
    public void addCronTask(Runnable task, String cronExpression)
    {
        addCronTask(new CronTask(task, cronExpression));
    }

    public void addCronTask(CronTask cronTask)
    {
        if (cronTask != null)
        {
            Runnable task = cronTask.getRunnable();
            if (this.scheduledTasks.containsKey(task))
            {
                removeCronTask(task);
            }
            this.scheduledTasks.put(task, scheduleCronTask(cronTask));
        }
    }

    /**
     * 移除定时任务
     *
     * @param task
     */
    public void removeCronTask(Runnable task)
    {
        ScheduledTask scheduledTask = this.scheduledTasks.remove(task);
        if (scheduledTask != null)
        {
            scheduledTask.cancel();
        }
    }

    public ScheduledTask scheduleCronTask(CronTask cronTask)
    {
        ScheduledTask scheduledTask = new ScheduledTask();
        scheduledTask.future = this.taskScheduler.schedule(cronTask.getRunnable(), cronTask.getTrigger());

        return scheduledTask;
    }

    @Override
    public void destroy() throws Exception
    {
        for (ScheduledTask task : scheduledTasks.values())
        {
            task.cancel();
        }
        this.scheduledTasks.clear();
    }
}
