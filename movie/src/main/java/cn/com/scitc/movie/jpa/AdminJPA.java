package cn.com.scitc.movie.jpa;

import cn.com.scitc.movie.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJPA extends JpaRepository<AdminEntity, Integer> {

    /**
     * 根据账号 查询 是否被注册
     * @param account
     * @return
     */
    AdminEntity findByAccount(String account);
}
