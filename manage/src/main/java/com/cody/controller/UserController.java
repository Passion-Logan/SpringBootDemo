package com.cody.controller;

import com.alibaba.fastjson.JSON;
import com.cody.entity.UserEntity;
import com.cody.jpa.UserJPA;
import com.cody.service.UserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Cody_
 * @date 18/9/25
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserJPA userJPA;

    /**
     * 返回所有用户，搜索调用此方法，删除调用此方法
     * 当搜索用户学号不为空时，将会返回搜索后的数据
     * 当删除用户的学号不为空时，将会根据用户学号删除用户
     * 分页 前端设置每页10条
     * @param username 搜索用户的学号
     * @param delUsername 删除用户的学号
     * @return
     */
    @RequestMapping("/allUsers")
    @ResponseBody
    public Object all(@Param("username")String username,
                      @Param("delUsername")String delUsername,
                      @Param("page")Integer page,
                      @Param("limit")Integer limit) {

        //所有用户
        List<UserEntity> selectAll = userService.all();
        //分页后的所有用户
        List<UserEntity> all = userService.alluser(page, limit);

        //搜索
        if(username != null) {
            UserEntity findUser = userJPA.findByUsername(username);
            List<UserEntity> userEntities = new ArrayList<>();

            if(findUser != null) {
                userEntities.add(findUser);

                Map findMap = new HashMap();
                findMap.put("data", userEntities);
                findMap.put("code", 0);

                return JSON.toJSONString(findMap);
            }else {
                Map findMap = new HashMap();
                findMap.put("msg", "没有数据");

                return JSON.toJSONString(findMap);
            }
        }

        //删除
        if(delUsername != null) {
            userJPA.deleteByUsername(delUsername);

            List<UserEntity> newAll = userService.alluser(page, limit);

            Map map = new HashMap<>();
            map.put("code", 0);
            map.put("msg", "加载成功！");
            map.put("count", selectAll.size());
            map.put("data", newAll);

            return JSON.toJSONString(map);
        }

        Map map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "加载成功！");
        map.put("count", selectAll.size());
        map.put("data", all);

        return JSON.toJSONString(map);
    }

    /**
     * 根据登录用户学号，返回用户的信息，展示到个人信息界面
     * @param model
     * @return
     */
    @GetMapping("/page/userInfo")
    public ModelAndView userInfo(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        UserEntity user = userJPA.findByUsername(userDetails.getUsername());

        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("name", user.getName());
        model.addAttribute("department", user.getDepartment());
        model.addAttribute("clazz", user.getClazz());
        model.addAttribute("qq", user.getQq());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("tel", user.getTel());
        model.addAttribute("roles", userDetails.getAuthorities());

        return new ModelAndView("page/userInfo", "userInfo", model);
    }

    /**
     * 用户修改个人信息
     * @param name 姓名
     * @param department 系部
     * @param clazz 班级
     * @param qq QQ
     * @param email 电子邮箱
     * @param tel 联系电话
     * @return
     */
    @RequestMapping("/update")
    public String update(@Param("name") String name,
                         @Param("department") String department,
                         @Param("clazz") String clazz,
                         @Param("qq") String qq,
                         @Param("email") String email,
                         @Param("tel") String tel) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        UserEntity oldUser = userJPA.findByUsername(userDetails.getUsername());

        oldUser.setName(name);
        oldUser.setDepartment(department);
        oldUser.setClazz(clazz);
        oldUser.setQq(qq);
        oldUser.setTel(tel);
        oldUser.setEmail(email);

        userJPA.save(oldUser);

        return "redirect:page/userInfo?success";
    }

    /**
     * 用户修改密码
     * @param oldPwd
     * @param newPwd
     * @param pwd
     * @param model
     * @return
     */
    @RequestMapping("/changePwd")
    public ModelAndView changePwd(@Param("oldPwd")String oldPwd,
                            @Param("newPwd")String newPwd,
                            @Param("pwd")String pwd,
                                  Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        UserEntity oldUser = userJPA.findByUsername(userDetails.getUsername());

        model.addAttribute("username", userDetails.getUsername());

        System.out.println("输入的旧密码:" + oldPwd);
        System.out.println("输入的新密码:" + newPwd);

        boolean oldPwdFlag = BCrypt.checkpw(oldPwd, oldUser.getPassword());

        if(!oldPwdFlag) {
            return new ModelAndView("redirect:page/changePwd?oldError", "changePwd", model);
        }

        if(newPwd.equals(pwd) == false) {
            return new ModelAndView("redirect:page/changePwd?pwdError", "changePwd", model);
        }

        String newPassword = BCrypt.hashpw(newPwd, BCrypt.gensalt());
        oldUser.setPassword(newPassword);
        userJPA.save(oldUser);

        return new ModelAndView("redirect:page/changePwd?success", "changePwd", model);
    }

    /**
     * 管理员修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/updateAdmin")
    @ResponseBody
    public String updateUser(@ModelAttribute(value = "userAdmin")UserEntity user) {
        UserEntity getUser = userJPA.findByUsername(user.getUsername());

        if(user.getUsername().equals("16301216")) {
            getUser.setRoles(getUser.getRoles());
            getUser.setEmail(getUser.getEmail());
            getUser.setTel(getUser.getTel());
            getUser.setQq(getUser.getQq());
            getUser.setDepartment(getUser.getDepartment());
            getUser.setName(getUser.getName());
            getUser.setClazz(getUser.getClazz());
            getUser.setUsername(getUser.getUsername());
        }else {
            getUser.setRoles(user.getRoles());
            getUser.setEmail(user.getEmail());
            getUser.setTel(user.getTel());
            getUser.setQq(user.getQq());
            getUser.setDepartment(user.getDepartment());
            getUser.setName(user.getName());
            getUser.setClazz(user.getClazz());
            getUser.setUsername(user.getUsername());
        }

        userService.updateUser(getUser);

        return "修改成功!";
    }
}
