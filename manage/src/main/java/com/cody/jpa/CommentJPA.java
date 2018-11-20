package com.cody.jpa;

import com.cody.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentJPA extends JpaRepository<CommentEntity, Long> {

    /**
     * 根据用户姓名查找留言
     * @param username  留言用户的姓名
     * @return
     */
    List<CommentEntity> findByUsername(String username);
}
