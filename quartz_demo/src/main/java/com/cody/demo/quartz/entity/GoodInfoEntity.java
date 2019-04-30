package com.cody.demo.quartz.entity;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @File Name: com.cody.demo.quartz.entity
 * @Author: WQL //作者及
 * @Date: 2019/4/30 14:07//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Entity
@Table(name = "basic_good_info")
@Data
public class GoodInfoEntity
{
    /**
     * 商品编号
     */
    @Id
    @GeneratedValue
    @Column(name = "bgi_id")
    private Long id;
    /**
     * 商品名称
     */
    @Column(name = "bgi_name")
    private String name;
    /**
     * 商品单位
     */
    @Column(name = "bgi_unit")
    private String unit;
    /**
     * 商品单价
     */
    @Column(name = "bgi_price")
    private BigDecimal price;
}