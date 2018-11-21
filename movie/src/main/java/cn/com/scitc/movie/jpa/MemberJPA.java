package cn.com.scitc.movie.jpa;

import cn.com.scitc.movie.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberJPA extends JpaRepository<MemberEntity, Integer> {

    /**
     * 根据账号 查询 是否被注册
     * @param account
     * @return
     */
    MemberEntity findByAccount(String account);

    /**
     * 根据 昵称查询用户
     * @param nickname
     * @return
     */
    Optional<MemberEntity> findByNickname(String nickname);
}
