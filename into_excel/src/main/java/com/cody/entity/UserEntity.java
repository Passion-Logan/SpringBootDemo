package com.cody.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @File Name: com.cody.entity
 * @Author: WQL //作者及
 * @Date: 2019/4/17 14:30//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Entity
@Table(name = "poi_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private long age;

    @Column(name = "tel")
    private String tel;
}