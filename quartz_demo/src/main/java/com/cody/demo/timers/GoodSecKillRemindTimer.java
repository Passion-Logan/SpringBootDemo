package com.cody.demo.timers;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @File Name: com.cody.demo.timers
 * @Author: WQL //作者及
 * @Date: 2019/4/30 14:36//完成日期
 * @Description: // 商品秒杀提醒定时器，为关注该秒杀商品的用户进行推送提醒
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class GoodSecKillRemindTimer extends QuartzJobBean
{

    private Logger logger = LoggerFactory.getLogger(GoodSecKillRemindTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        // 获取任务详情内的数据集合
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        // 获取任务编号
        Long goodId = dataMap.getLong("goodId");

        logger.info("分布式节点quartz-cluster-node-first，开始处理秒杀商品：{}，关注用户推送消息.", goodId);

    }
}