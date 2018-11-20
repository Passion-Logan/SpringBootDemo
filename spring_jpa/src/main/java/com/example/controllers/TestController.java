package com.example.controllers;

import com.example.entity.UsersEntity;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class TestController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add")
    public ModelAndView addUser(UsersEntity usersEntity) {
        userService.saveUser(usersEntity);

        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping(value = "/update")
    public ModelAndView updateUser(UsersEntity usersEntity) {
        userService.updateUser(usersEntity);

        return new ModelAndView("redirect:/user/list");
    }

    @RequestMapping(value = "/del/{id}")
    public ModelAndView deleteUser(@PathVariable("id")Integer id) {
        userService.deleteUser(id);

        return new ModelAndView("redirect:/user/list");
    }

    @ResponseBody
    @RequestMapping(value = {"/list", "/"})
    public ModelAndView allUser(Model model) {
        List<UsersEntity> userEntities = userService.listUser();

        model.addAttribute("allUser", userEntities);
        model.addAttribute("result", "用户信息表");

        return new ModelAndView("index", "userModel", model);
    }
}
