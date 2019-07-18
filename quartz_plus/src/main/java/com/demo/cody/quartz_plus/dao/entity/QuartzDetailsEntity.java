package com.demo.cody.quartz_plus.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @File Name: com.demo.cody.quartz_plus.dao.entity
 * @Author: WQL //作者及
 * @Date: 2019/7/18 17:20//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Entity
@ApiModel(value = "定时任务实体表")
@Table(name = "quartz_details")
@Data
public class QuartzDetailsEntity implements Serializable
{
    @ApiModelProperty(value = "主键ID,新增时为空,修改不能为空")
    @Id
    @GeneratedValue(generator = "ID", strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private String id;

    @ApiModelProperty(value = "定时任务名称")
    @NotBlank(message = "定时任务名称不能为空")
    @GeneratedValue(generator = "ID", strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private String jobName;
    private String jobGroup;
    private String taskId;
    private String cronExpression;
    private String remark;
    private Long dOpdt;
    private String dFlag;
    private String dOper;

}