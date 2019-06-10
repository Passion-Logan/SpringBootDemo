package com.cody.service;

import com.cody.dto.LogDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @File Name: com.cody.service
 * @Author: WQL //作者及
 * @Date: 2019/6/10 16:57//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class CommonLogService
{
    private static final Logger log= LoggerFactory.getLogger(CommonLogService.class);

    /**
     * 通用的写日志服务逻辑
     */
    public void insertLog(LogDto dto){
        log.info("通用的写日志服务逻辑 数据；{} ",dto);

    }
}