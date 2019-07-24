package com.demo.cody.dao.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

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
    @Column(name = "JOB_NAME")
    private String jobName;

    @ApiModelProperty(value = "任务组")
    @NotBlank(message = "任务组不能为空")
    @Column(name = "JOB_GROUP")
    private String jobGroup;

    @ApiModelProperty(value = "任务ID")
    @NotBlank(message = "任务ID不能为空")
    @Column(name = "TASK_ID")
    private String taskId;

    @ApiModelProperty(value = "时间表达式,只能含有英文、数字、空格、？、*、,、/、#")
    @NotBlank(message = "时间表达式不能为空")
    @Column(name = "CRON_EXPRESSION")
    private String cronExpression;

    @ApiModelProperty(value = "备注,不能超过300个字符")
    @Length(max = 300, message = "备注不能超过300个字符")
    @Column(name = "REMARK")
    private String remark;

    @ApiModelProperty(hidden = true)
    @Column(name = "D_OPDT_" ,nullable = true, precision = 0)
    private Long dOpdt;

    @ApiModelProperty(hidden = true)
    @Column(name = "D_FLAG_", nullable = true, length = 1)
    private String dFlag;

    @ApiModelProperty(hidden = true)
    @Column(name = "D_OPER_", nullable = true, length = 36)
    private String dOper;

}