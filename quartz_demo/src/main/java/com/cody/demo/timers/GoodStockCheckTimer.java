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
 * @Date: 2019/4/30 14:48//完成日期
 * @Description: // 商品库存检查定时任务
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class GoodStockCheckTimer extends QuartzJobBean
{

    static Logger logger = LoggerFactory.getLogger(GoodStockCheckTimer.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        logger.info("分布式节点quartz-cluster-node-first，执行库存检查定时任务，执行时间：{}", new Date());
    }
}