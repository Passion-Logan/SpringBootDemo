package cn.com.scitc.movie.service;

import cn.com.scitc.movie.entity.AdminEntity;
import org.springframework.stereotype.Service;

public interface AdminService {

    /**
     * 根据账号查询账号是否存在
     * @param account
     * @return
     */
    AdminEntity findByAccount(String account);
}
