package cn.com.scitc.movie.controller;

import cn.com.scitc.movie.entity.AdminEntity;
import cn.com.scitc.movie.entity.MemberEntity;
import cn.com.scitc.movie.jpa.AdminJPA;
import cn.com.scitc.movie.jpa.MemberJPA;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登录控制器
 * 包括管理员登录 会员登录
 */
@RestController
public class LoginController {

    @Autowired
    MemberJPA memberJPA;

    @Autowired
    AdminJPA adminJPA;

    static Map<String , Object> map = Collections.synchronizedMap(new HashMap<>());

    /**
     * 会员登录
     * @param account
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login/member")
    public Object member(@Param("account")String account,
                         @Param("password")String password,
                         HttpServletRequest request) {
        //登录成功
        boolean flag = true;
        String msg = "登录成功!";
        Integer code = 0;
        String role = "ROOT_MEMBER";

        //根据账号查询用户是否存在
        MemberEntity memberEntity = memberJPA.findByAccount(account);

        try {
            //用户不存在
            if(memberEntity == null) {
                flag = false;
                code = 1;

                msg = "用户不存在，登录失败！";
                map.put("code", code);
                map.put("msg", msg);
            }
            //密码错误
            else if(!memberEntity.getPassword().equals(password)){
                flag = false;
                code = 1;
                msg = "密码错误，登录失败！";

                map.put("code", code);
                map.put("msg", msg);
            }

            //登录成功
            if(flag) {
                //将用户写入session
                request.getSession().setAttribute("_session_user", memberEntity);
                request.getSession().setAttribute("role", role);
                request.getSession().setAttribute("account", memberEntity.getAccount());
                request.getSession().setAttribute("nickname", memberEntity.getNickname());

                map.put("code", code);
                map.put("msg", msg);
                map.put("role", role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(map);
    }

    /**
     * 管理员登录
     * @param account
     * @param password
     * @param request
     * @return
     */
    @RequestMapping("/login/admin")
    public Object admin(@Param("account")String account,
                         @Param("password")String password,
                         HttpServletRequest request) {
        //登录成功
        boolean flag = true;
        String msg = "登录成功!";
        Integer code = 0;
        String role = "ROOT_ADMIN";

        //根据账号查询用户是否存在
        AdminEntity adminEntity = adminJPA.findByAccount(account);

        //用户不存在
        if(adminEntity == null) {
            flag = false;
            code = 1;
            msg = "用户不存在，登录失败！";

            map.put("code", code);
            map.put("msg", msg);
        }
        //密码错误
        else if(!adminEntity.getPassword().equals(password)){
            flag = false;
            code = 1;
            msg = "密码错误，登录失败！";

            map.put("code", code);
            map.put("msg", msg);
        }

        //登录成功
        if(flag) {
            //将用户写入session
            request.getSession().setAttribute("_session_user", adminEntity);
            request.getSession().setAttribute("role", role);
            request.getSession().setAttribute("account", adminEntity.getAccount());
            request.getSession().setAttribute("nickname", adminEntity.getNickname());

            map.put("code", code);
            map.put("msg", msg);
            map.put("role", role);
        }

        return JSON.toJSONString(map);
    }

    /**
     * 退出登录
     * @return
     */
    @RequestMapping({"/logout/member", "/logout/admin"})
    public Object out(HttpServletRequest request) {
        try {
            request.getSession().removeAttribute("_session_user");
        } catch (Exception e) {
            e.printStackTrace();

            map.put("code", 1);
            map.put("msg", "太丑了，不许退出！");
            return JSON.toJSONString(map);
        }

        map.put("code", 0);
        map.put("msg", "太帅了，退下！");
        return JSON.toJSONString(map);
    }

}
