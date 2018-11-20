package com.cody.service.impl;

import com.cody.constant.RoleConstant;
import com.cody.entity.ActivityEntity;
import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Cody_
 * @date 18/10/5
 */
@Service
public class BaseUserService implements UserService {

    @Autowired
    private final UserJPA userJPA;

    public BaseUserService(UserJPA userJPA) {
        this.userJPA = userJPA;
    }


    /**
     * 用户注册
     * @param userEntity
     * @return
     */
    @Override
    public boolean insert(UserEntity userEntity) {
        String username = userEntity.getUsername();
        if(exist(username)) {
            return false;
        }
        userEntity.setRoles(RoleConstant.ROLE_USER);
        userJPA.save(userEntity);
        return true;
    }

    /**
     * 根据用户姓名查找用户
     * @param username 学号
     * @return
     */
    @Override
    public UserEntity getByUserName(String username) {
        return userJPA.findByUsername(username);
    }

    /**
     * 返回所有用户
     * @return
     */
    @Override
    public List<UserEntity> alluser(Integer page, Integer limit) {
        Pageable pageable = PageRequest.of(page-1, limit, Sort.Direction.ASC, "id");
        Page<UserEntity> userEntityPage = userJPA.findAll(pageable);

        Iterator<UserEntity> userEntityIterator =  userEntityPage.iterator();

        //分页后的List
        List<UserEntity> usersAll = new ArrayList<>();

        while(userEntityIterator.hasNext()) {
            usersAll.add(userEntityIterator.next());
        }

        //筛选后的List
        /*List<UserEntity> users = new ArrayList<>();
        for(UserEntity user : usersAll) {
            if(!user.getRoles().equals("ROLE_ROOT")) {
                users.add(user);
            }
        }*/

        return usersAll;
    }

    @Override
    public List<UserEntity> all() {
        return userJPA.findAll();
    }

    /**
     * 根据用户学号删除用户
     * @param username
     */
    @Override
    public void deleteUser(String username) {
        userJPA.deleteByUsername(username);
    }

    /**
     * 修改用户信息
     * @param userEntity 用户实体
     */
    @Override
    public void updateUser(UserEntity userEntity) {
        userJPA.save(userEntity);
    }

    /**
     * 判断用户是否存在
     * @param username 学号
     * @return
     */
    private boolean exist(String username) {
        UserEntity userEntity = userJPA.findByUsername(username);

        return (userEntity != null);
    }
}
