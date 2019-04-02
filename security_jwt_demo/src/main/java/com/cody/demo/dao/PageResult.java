package com.cody.demo.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @File Name: com.cody.demo.dao
 * @Author: WQL //作者及
 * @Date: 2019/4/2 16:03//完成日期
 * @Description: // 分页结果DO
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T>
{
    private int page;
    private int rows;
    private int total;
    private T data;
}