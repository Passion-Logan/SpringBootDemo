package com.cody.jpa;

import com.cody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.transaction.Transactional;
import java.io.Serializable;

public interface UserJPA extends
        JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable
{
    /**
     *
     * 根据用户学号查找用户
     * 判断用户学号是否重复注册
     * @param username 学号
     * @return
     */
    UserEntity findByUsername(String username);

    /**
     * 根据用户学号删除用户
     * @param username 学号
     */
    @Transactional
    void deleteByUsername(String username);

}
