package com.cody.demo.exception;

import com.cody.demo.dao.ResultJson;
import lombok.Getter;

/**
 * @File Name: com.cody.demo.exception
 * @Author: WQL //作者及
 * @Date: 2019/4/3 10:14//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Getter
public class CustomException extends RuntimeException
{
    private ResultJson resultJson;

    public CustomException(ResultJson resultJson)
    {
        this.resultJson = resultJson;
    }
}
