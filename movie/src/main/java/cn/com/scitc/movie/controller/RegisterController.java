package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.entity.MemberEntity;
import cn.com.scitc.movie.service.MemberService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class RegisterController {

    @Autowired
    MemberService memberService;

    static Map<String , Object> map = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping("/regist/member")
    public Object regist(@Param("account")String account,
                         @Param("nickname")String nickname,
                         @Param("password")String password) {
        List<MemberEntity> memberEntity = memberService.findByAccount(account);

        try {
            if(memberEntity != null && memberEntity.size() != 0) {
                map.put("code", 1);
                map.put("msg", "注册失败，该账户已被注册！");
            }else {
                MemberEntity regist = new MemberEntity();
                regist.setAccount(account);
                regist.setNickname(nickname);
                regist.setPassword(password);

                memberService.save(regist);

                map.put("code", 0);
                map.put("msg", "注册成功，请前往登陆！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }
}
