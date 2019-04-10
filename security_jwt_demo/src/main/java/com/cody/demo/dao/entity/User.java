package com.cody.demo.dao.entity;

import lombok.*;

import javax.validation.constraints.Size;

/**
 * @File Name: com.cody.demo.dao.entity
 * @Author: WQL //作者及
 * @Date: 2019/4/2 15:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User
{
    @Size(min = 1, max = 20)
    private String name;
    @Size(min = 1, max = 20)
    private String password;
}