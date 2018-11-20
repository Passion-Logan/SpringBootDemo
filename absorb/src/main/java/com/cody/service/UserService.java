package com.cody.service;

import com.cody.entity.UserEntity;

/**
 * @author Cody_
 * @date 18/9/18
 */
public interface UserService {

    /**
     * 用户注册逻辑
     * @param userEntity
     * @return
     */
    String register(UserEntity userEntity);
}
