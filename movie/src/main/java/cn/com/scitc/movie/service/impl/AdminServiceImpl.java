package cn.com.scitc.movie.service.impl;

import cn.com.scitc.movie.entity.AdminEntity;
import cn.com.scitc.movie.jpa.AdminJPA;
import cn.com.scitc.movie.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminJPA adminJPA;

    /**
     * 根据账号查询用户是否存在
     * @param account
     * @return
     */
    @Override
    public AdminEntity findByAccount(String account) {
        return adminJPA.findByAccount(account);
    }
}
