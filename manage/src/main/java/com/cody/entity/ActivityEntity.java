package com.cody.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * @author Cody_
 * @date 18/10/17
 */
@Data
@Entity
public class ActivityEntity {

    /**
     * 活动主键
     */
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 活动名称
     */
    @Column(name = "name", nullable = false)
    private String name;


    /**
     * 活动地点
     */
    @Column(name = "site", nullable = false)
    private String site;

    /**
     * 活动的简介
     * @Lob 大对象，映射 MySQL 的 Long Text 类型
     * @Basic 懒加载
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "简介不能为空")
    @Size(max = 100000, message = "内容不能超过100000个字符")
    @Column(name = "intro", nullable = false)
    private String intro;

    /**
     * 活动的状态
     */
    @Column(name = "flag", nullable = false)
    private Integer flag;

    /**
     * 报名状态
     */
    @Column(name = "apply", nullable = false)
    private Integer apply;

    /**
     * 活动开始时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "start", nullable = false)
    private Timestamp start;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public Integer getApply() {
        return apply;
    }

    public void setApply(Integer apply) {
        this.apply = apply;
    }

    public Timestamp getStart() {
        return start;
    }

    public void setStart(Timestamp start) {
        this.start = start;
    }
}
