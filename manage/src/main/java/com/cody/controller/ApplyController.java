package com.cody.controller;

import com.cody.entity.ApplyEntity;
import com.cody.entity.UserEntity;
import com.cody.jpa.ApplyJPA;
import com.cody.jpa.UserJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplyController {

    @Autowired
    UserJPA userJPA;

    @Autowired
    ApplyJPA applyJPA;

    /**
     * 添加报名信息
     * @param id 报名活动Id
     * @return
     */
    @RequestMapping(value = "/joinActivity")
    @ResponseBody
    public Object joinActivity(@Param(value = "id")Long id) {

        //获取登录用户的学号
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        UserEntity userEntity = userJPA.findByUsername(username);

        ApplyEntity applyEntity = new ApplyEntity();
        applyEntity.setActivity_id(id);
        applyEntity.setUsername(username);
        applyEntity.setName(userEntity.getName());

        applyJPA.save(applyEntity);

        if(applyJPA.save(applyEntity).getActivity_id().equals(id)) {
            Map map = new HashMap();
            map.put("code", 0);
            map.put("msg", "终于等到你，报名成功");

            return map;
        }

        Map map = new HashMap();
        map.put("code", 1);
        map.put("msg", "太帅了，报名失败！");

        return map;
    }
}
