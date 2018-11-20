package com.cody.service;

import com.cody.entity.UserEntity;

import java.util.List;

/**
 * @author Cody_
 * @date 18/9/26
 */
public interface UserService {

    /**
     * 用户注册逻辑
     * 添加新用户
     * username 唯一，默认 USER 权限
     * @param userEntity
     * @return
     */
    boolean insert(UserEntity userEntity);

    /***
     * 查询用户信息
     * @param username 学号
     * @return UserEntity
     */
    UserEntity getByUserName(String username);

    /**
     * 根据分页获取所有用户
     * @page 页数
     * @param limit 每页条数
     * @return 除ROOT用户以外的所有用户
     */
    List<UserEntity> alluser(Integer page, Integer limit);

    /**
     * 获取所有用户
     * @return 除ROOT用户以外的所有用户
     */
    List<UserEntity> all();

    /**
     * 根据学号删除用户
     * @param username
     */
    void deleteUser(final String username);

    /**
     * 更新用户信息
     * @param userEntity 用户实体
     */
    void updateUser(final UserEntity userEntity);
}
