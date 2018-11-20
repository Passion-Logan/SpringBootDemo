package com.cody.controller;

import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class SaveUser {

    @Autowired
    UserJPA userJPA;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register")
    public String register(Model model,
                           @ModelAttribute(value = "user")UserEntity userEntity,
                           HttpServletResponse response) {

        String password = userEntity.getPassword();
        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));

        //使用userService处理业务
        String result = userService.register(userEntity);
        model.addAttribute("result", result);

        return response.encodeRedirectURL("index");
    }
}
