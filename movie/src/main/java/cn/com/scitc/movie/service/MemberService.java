package cn.com.scitc.movie.service;

import cn.com.scitc.movie.entity.MemberEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    /**
     * 会员注册
     * @param memberEntity
     */
    void register(MemberEntity memberEntity);

    /**
     * 根据账号查询用户
     * @param account
     * @return
     */
    Optional<MemberEntity> findByAccount(String account);

    /**
     * 根据编号查询用户
     * @param id
     * @return
     */
    Optional<MemberEntity> findById(Integer id);

    /**
     * 根据昵称查询用户
     * @param nickname
     * @return
     */
    Optional<MemberEntity> findByNickname(String nickname);

    /**
     * 添加会员
     * @param memberEntity
     */
    void save(MemberEntity memberEntity);
}
