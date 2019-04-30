package com.cody.demo.timers;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @File Name: com.cody.demo.timers
 * @Author: WQL //作者及
 * @Date: 2019/4/30 14:21//完成日期
 * @Description: // 商品添加定时任务实现类
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class GoodAddTimer extends QuartzJobBean
{

    static Logger logger = LoggerFactory.getLogger(GoodAddTimer.class);

    /**
     * 定时任务逻辑实现方法
     * 每当触发器触发时会执行改方法逻辑
     *
     * @param jobExecutionContext 任务执行上下文
     * @throws JobExecutionException
     */
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        logger.info("分布式节点quartz-cluster-node-first，商品添加完成后执行任务，任务时间：{}", new Date());
    }
}