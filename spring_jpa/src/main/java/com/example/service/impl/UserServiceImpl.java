package com.example.service.impl;

import com.example.dao.Jpa;
import com.example.entity.UsersEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Jpa userJPA;

    @Override
    public List<UsersEntity> listUser() {
        return userJPA.findAll();
    }

    @Override
    public void deleteUser(Integer id) {
        userJPA.deleteById(id);
    }

    @Override
    public UsersEntity saveUser(UsersEntity usersEntity) {
        return userJPA.save(usersEntity);
    }

    @Override
    public UsersEntity updateUser(UsersEntity usersEntity) {
        return userJPA.save(usersEntity);
    }
}
