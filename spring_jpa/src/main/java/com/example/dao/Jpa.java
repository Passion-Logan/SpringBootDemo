package com.example.dao;

import com.example.entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Jpa extends JpaRepository<UsersEntity, Integer> {

}
