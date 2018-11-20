package com.cody.controller;

import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author Cody_
 * @date 18/9/25
 */
@Controller
@AllArgsConstructor
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    UserJPA userJPA;

    @GetMapping({"/", "/index", "/home"})
    public String root() {
        return "index";
    }

    @GetMapping("/page/welcome")
    public String welcome() {
        return "page/welcome";
    }

    @GetMapping("/page/login")
    public String login() {
        return "page/login";
    }

    @GetMapping("/page/register")
    public String register() {
        return "page/register";
    }

    @GetMapping("/login")
    public String loginError() {
        return "page/login";
    }

    @GetMapping("/page/changePwd")
    public ModelAndView changePwd(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        UserEntity oldUser = userJPA.findByUsername(userDetails.getUsername());

        model.addAttribute("username", userDetails.getUsername());

        return new ModelAndView("page/changePwd", "changePwd", model);
    }

    @RequestMapping("/page/allUsers")
    public String all() {
        return "page/allUsers";
    }

    @RequestMapping("/page/userAdmin")
    public String userAdmin() {
        return "page/userAdmin";
    }

    @RequestMapping("/register")
    public String doRegister(UserEntity userEntity){

        String password = userEntity.getPassword();

        userEntity.setPassword(new BCryptPasswordEncoder().encode(password));

        //使用userService处理业务
        if (userService.insert(userEntity)) {
            return "redirect:page/register?success";
        }

        return "redirect:page/register?error";
    }

    /**
     * 留言板控制器
     * @return
     */
    @RequestMapping("/words")
    public String words() {
        return "words/index";
    }

    /**
     * 留言板管理界面控制器
     * @return
     */
    @RequestMapping("/wordsAdmin")
    public String wordsAdmin() {
        return "words/wordsAdmin";
    }

    /**
     * 活动展示界面控制器
     * @return
     */
    @RequestMapping("/activity")
    public String activity() {
        return "activity/index";
    }

    /**
     * 活动管理控制器
     * @return
     */
    @RequestMapping("/activityAdmin")
    public String activityAdmin() {
        return "activity/activityAdmin";
    }

    /**
     * 关于我们
     * @return
     */
    @RequestMapping(value = "/about")
    public String about() {
        return "page/about";
    }
}
