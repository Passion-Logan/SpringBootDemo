package cn.com.scitc.movie.service.impl;

import cn.com.scitc.movie.entity.MemberEntity;
import cn.com.scitc.movie.jpa.MemberJPA;
import cn.com.scitc.movie.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    MemberJPA memberJPA;

    @Override
    public void register(MemberEntity memberEntity) {
        memberJPA.save(memberEntity);
    }

    @Override
    public Optional<MemberEntity> findByAccount(String account) {
        return memberJPA.findByAccount(account);
    }

    @Override
    public Optional<MemberEntity> findById(Integer id) {
        return memberJPA.findById(id);
    }

    @Override
    public Optional<MemberEntity> findByNickname(String nickname) {
        return memberJPA.findByNickname(nickname);
    }

    @Override
    public void save(MemberEntity memberEntity) {
        memberJPA.save(memberEntity);
    }
}
