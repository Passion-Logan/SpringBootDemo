package com.example.service;

import com.example.entity.UsersEntity;

import java.util.List;

public interface UserService {

    List<UsersEntity> listUser();

    void deleteUser(final Integer id);

    UsersEntity saveUser(UsersEntity usersEntity);

    UsersEntity updateUser(UsersEntity usersEntity);
}
