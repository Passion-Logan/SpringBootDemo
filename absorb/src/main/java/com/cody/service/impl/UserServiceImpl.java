package com.cody.service.impl;

import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserJPA userJPA;

    /**
     * 用户注册逻辑
     * @param userEntity
     * @return
     */
    @Override
    public String register(UserEntity userEntity) {
        UserEntity user = userJPA.findByUsername(userEntity.getUsername());

        //判读用户是否存在
        if(user == null) {
            userJPA.save(userEntity);
            return "注册成功!";
        }
        else {
            return "<" + user.getUsername() + "已经被注册!" + ">";
        }
    }
}
