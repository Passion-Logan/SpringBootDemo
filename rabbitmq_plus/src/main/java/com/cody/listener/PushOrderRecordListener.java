package com.cody.listener;

import com.cody.entity.OrderRecordEntity;
import com.cody.listener.event.PushOrderRecordEvent;
import com.cody.mapper.OrderRecordEntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;

/**
 * @File Name: com.cody.listener
 * @Author: WQL //作者及
 * @Date: 2019/6/10 15:21//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
public class PushOrderRecordListener implements ApplicationListener<PushOrderRecordEvent>
{

    private static final Logger log= LoggerFactory.getLogger(PushOrderRecordListener.class);

    @Autowired
    private OrderRecordEntityMapper orderRecordMapper;

    @Override
    public void onApplicationEvent(PushOrderRecordEvent event) {
        log.info("监听到的下单记录： {} ",event);

        try {
            if (event!=null){
                OrderRecordEntity entity=new OrderRecordEntity();
                BeanUtils.copyProperties(event,entity);
                orderRecordMapper.insertSelective(entity);
            }
        }catch (Exception e){
            log.error("监听下单记录发生异常：{} ",event,e.fillInStackTrace());
        }
    }

}