package com.cody.jpa;

import com.cody.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.io.Serializable;

public interface UserJPA extends
        JpaRepository<UserEntity, Long>,
        JpaSpecificationExecutor<UserEntity>,
        Serializable
{

    /**
     * 根据用户学号查找用户
     * 判断用户学号是否重复注册
     * @param username
     * @return
     */
    UserEntity findByUsername(String username);
}
