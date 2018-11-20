package com.cody.entity;

import com.alibaba.fastjson.annotation.JSONField;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * @author Cody_
 * @date 18/10/4
 */
@Entity
public class CommentEntity {

    /**
     * 留言的ID 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 留言的内容
     * @Lob 大对象，映射 MySQL 的 Long Text 类型
     * @Basic 懒加载
     *
     */
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @NotEmpty(message = "内容不能为空")
    @Size(max = 1024, message = "内容不能超过1024个字符")
    @Column(name = "content", nullable = false)
    private String content;

    /**
     * 用户学号
     */
    @Column(name = "user_id", nullable = false)
    private String userId;

    /**
     * 用户姓名
     */
    @Column(name = "user_name", nullable = false)
    private String username;

    /**
     * 留言的时间
     * 由数据库自动创建时间
     */
    @JSONField(format = "yyyy-MM-dd")
    @Column(name = "create_time", nullable = false, columnDefinition = "datetime COMMENT '留言时间'")
    @CreationTimestamp
    private Timestamp createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}

