package com.cody.jpa;

import com.cody.entity.ApplyEntity;
import com.cody.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyJPA extends JpaRepository<ApplyEntity, Long> {

    /**
     * 获取指定用户的报名信息
     * @param username 用户的学号
     * @return
     */
    List<ApplyEntity> findByUsername(String username);
}
