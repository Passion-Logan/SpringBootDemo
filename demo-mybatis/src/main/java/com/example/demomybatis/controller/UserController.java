package com.example.demomybatis.controller;

import com.example.demomybatis.entity.UserEntity;
import com.example.demomybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @File Name: com.example.demomybatis.controller
 * @Author: WQL //作者及
 * @Date: 2019/1/11 9:33//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
public class UserController
{

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/getUsers")
    public List<UserEntity> getUsers() {
        List<UserEntity> users= userMapper.getAll();
        return users;
    }

    @RequestMapping("/getUser/{id}")
    public UserEntity getUser(@PathVariable("id") Integer id) {
        UserEntity user=userMapper.getOne(id);
        return user;
    }

//    @RequestMapping("/add")
//    public void save(UserEntity user) {
//        userMapper.insert(user);
//    }
//
//    @RequestMapping(value="update")
//    public void update(UserEntity user) {
//        userMapper.update(user);
//    }

    @RequestMapping(value="/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userMapper.delete(id);
    }

}