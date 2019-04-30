package com.cody.demo.quartz.service;

import com.cody.demo.quartz.entity.GoodInfoEntity;
import com.cody.demo.quartz.jpa.GoodInfoRepository;
import com.cody.demo.timers.GoodAddTimer;
import com.cody.demo.timers.GoodSecKillRemindTimer;
import com.cody.demo.timers.GoodStockCheckTimer;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @File Name: com.cody.demo.quartz.service
 * @Author: WQL //作者及
 * @Date: 2019/4/30 14:13//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GoodInfoService
{

    /**
     * 注入任务调度器
     */
    @Autowired
    private Scheduler scheduler;

    /**
     * 商品数据接口
     */
    @Autowired
    private GoodInfoRepository goodInfoRepository;

    /**
     * 保存商品基本信息
     * @param good
     * @return
     * @throws SchedulerException
     */
    public Long saveGood(GoodInfoEntity good) throws SchedulerException
    {
        goodInfoRepository.save(good);
        // 构建创建商品定时任务
        buildCreateGoodTimer();
        // 构建商品库存定时任务
        buildGoodStockTimer();
        // 构建商品描述提醒定时任务
//        buildGoodSecKillRemindTimer(good.getId());
        return good.getId();
    }

    /**
     * 构建创建商品定时任务
     *
     * @throws SchedulerException
     */
    public void buildCreateGoodTimer() throws SchedulerException
    {
        // 设置开始时间为一分钟后
        long startAtTime = System.currentTimeMillis() + 1000 * 60;
        // 任务名称
        String name = UUID.randomUUID().toString();
        // 任务所属分组
        String group = GoodAddTimer.class.getName();
        // 创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodAddTimer.class).withIdentity(name, group).build();
        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startAtTime)).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 构建商品库存定时任务
     * 设置时间每隔30秒执行一次
     *
     * @throws SchedulerException
     */
    public void buildGoodStockTimer() throws SchedulerException
    {
        // 任务名称
        String name = UUID.randomUUID().toString();
        // 任务所属分组
        String group = GoodStockCheckTimer.class.getName();

        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");
        //创建任务
        JobDetail jobDetail = JobBuilder.newJob(GoodStockCheckTimer.class).withIdentity(name, group).build();
        //创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).withSchedule(scheduleBuilder).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }

    /**
     * 构建商品秒杀提醒定时任务
     * 时间设置为五分钟后执行
     *
     * @param goodId
     * @throws SchedulerException
     */
    public void buildGoodSecKillRemindTimer(Long goodId) throws SchedulerException
    {
        // 任务名称
        String name = UUID.randomUUID().toString();
        // 任务所属分组
        String group = GoodSecKillRemindTimer.class.getName();

        //秒杀开始时间
        long startTime = System.currentTimeMillis() + 1000 * 5 * 60;
        JobDetail jobDetail = JobBuilder
                .newJob(GoodSecKillRemindTimer.class)
                .withIdentity(name, group)
                .build();

        // 设置任务传递商品编号参数
        jobDetail.getJobDataMap().put("goodId", goodId);

        // 创建任务触发器
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity(name, group).startAt(new Date(startTime)).build();
        // 将触发器与任务绑定到调度器内
        scheduler.scheduleJob(jobDetail, trigger);
    }
}