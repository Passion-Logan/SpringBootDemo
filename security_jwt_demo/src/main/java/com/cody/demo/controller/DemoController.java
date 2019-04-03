package com.cody.demo.controller;

import com.cody.demo.dao.ResultCode;
import com.cody.demo.dao.ResultJson;
import com.cody.demo.dao.entity.ResponseUserToken;
import com.cody.demo.dao.entity.Role;
import com.cody.demo.dao.entity.User;
import com.cody.demo.dao.entity.UserDetail;
import com.cody.demo.service.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @File Name: com.cody.demo.controller
 * @Author: WQL //作者及
 * @Date: 2019/4/3 9:47//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@RestController
@RequestMapping("/api/demo")
public class DemoController
{
    @Value("${jwt.header}")
    private String tokenHeader;

    private final DemoService demoService;

    @Autowired
    public DemoController(DemoService demoService)
    {
        this.demoService = demoService;
    }

    /**
     * 登录
     *
     * @param user
     * @return
     */
    @PostMapping("/login")
    public ResultJson<ResponseUserToken> login(@Valid @RequestBody User user)
    {
        final ResponseUserToken response = demoService.login(user.getName(), user.getPassword());
        return ResultJson.ok(response);
    }

    /**
     * 登出
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/logout")
    public ResultJson logout(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        if (token == null)
        {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        demoService.logout(token);
        return ResultJson.ok();
    }

    /**
     * 根据token获取用户信息
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/user")
    public ResultJson getUser(HttpServletRequest request)
    {
        String token = request.getHeader(tokenHeader);
        if (token == null)
        {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = demoService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @PostMapping("/sign")
    public ResultJson sign(@RequestBody User user)
    {
        if (StringUtils.isAnyBlank(user.getName(), user.getPassword()))
        {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        UserDetail userDetail = new UserDetail(user.getName() ,user.getPassword(),Role.builder().id(1L).build());
        return ResultJson.ok(demoService.register(userDetail));
    }

    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    //    @GetMapping(value = "refresh")
    //    public ResultJson refreshAndGetAuthenticationToken(
    //            HttpServletRequest request){
    //        String token = request.getHeader(tokenHeader);
    //        ResponseUserToken response = authService.refresh(token);
    //        if(response == null) {
    //            return ResultJson.failure(ResultCode.BAD_REQUEST, "token无效");
    //        } else {
    //            return ResultJson.ok(response);
    //        }
    //    }
}