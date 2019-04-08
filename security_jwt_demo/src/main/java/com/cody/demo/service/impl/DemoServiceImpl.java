package com.cody.demo.service.impl;

import com.cody.demo.dao.ResultCode;
import com.cody.demo.dao.ResultJson;
import com.cody.demo.dao.entity.ResponseUserToken;
import com.cody.demo.dao.entity.Role;
import com.cody.demo.dao.entity.UserDetail;
import com.cody.demo.exception.CustomException;
import com.cody.demo.mapper.DemoMapper;
import com.cody.demo.service.DemoService;
import com.cody.demo.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @File Name: com.cody.demo.service.impl
 * @Author: WQL //作者及
 * @Date: 2019/4/3 9:53//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class DemoServiceImpl implements DemoService
{
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtils jwtUtils;
    private final DemoMapper demoMapper;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public DemoServiceImpl(AuthenticationManager authenticationManager, @Qualifier("CustomUserDetailsService") UserDetailsService userDetailsService, JwtUtils jwtUtils, DemoMapper demoMapper)
    {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtils = jwtUtils;
        this.demoMapper = demoMapper;
    }

    @Override
    public UserDetail register(UserDetail userDetail)
    {
        final String username = userDetail.getUsername();
        if (demoMapper.findByUsername(username) != null)
        {
            throw new CustomException(ResultJson.failure(ResultCode.BAD_REQUEST, "用户已存在"));
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userDetail.getPassword();
        userDetail.setPassword(encoder.encode(rawPassword));
        userDetail.setLastPasswordResetDate(new Date());
        demoMapper.insert(userDetail);
        long roleId = userDetail.getRole().getId();
        Role role = demoMapper.findRoleById(roleId);
        userDetail.setRole(role);
        demoMapper.insertRole(userDetail.getId(), roleId);
        return userDetail;
    }

    @Override
    public ResponseUserToken login(String username, String password)
    {
        // 用户验证
        final Authentication authentication = authenticate(username, password);
        // 存储认证信息
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成token
        final UserDetail userDetail = (UserDetail) authentication.getPrincipal();
        final String token = jwtUtils.generateAccessToken(userDetail);
        // 存储token
        jwtUtils.putToken(username, token);
        return new ResponseUserToken(token, userDetail);
    }

    @Override
    public void logout(String token)
    {
        token = token.substring(tokenHead.length());
        String userName = jwtUtils.getUsernameFromToken(token);
        jwtUtils.deleteToken(userName);
    }

    @Override
    public ResponseUserToken refresh(String oldToken)
    {
        String token = oldToken.substring(tokenHead.length());
        String username = jwtUtils.getUsernameFromToken(token);
        UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
        if (jwtUtils.canTokenBeRefreshed(token, userDetail.getLastPasswordResetDate()))
        {
            token = jwtUtils.refreshToken(token);
            return new ResponseUserToken(token, userDetail);
        }
        return null;
    }

    @Override
    public UserDetail getUserByToken(String token)
    {
        token = token.substring(tokenHead.length());
        return jwtUtils.getUserFromToken(token);
    }

    private Authentication authenticate(String username, String password)
    {
        try
        {
            // 该方法会去调用userDetailsService.loadUserByUsername()去验证用户名和密码，如果正确
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (Exception e)
        {
            throw new CustomException(ResultJson.failure(ResultCode.LOGIN_ERROR, e.getMessage()));
        }
    }
}