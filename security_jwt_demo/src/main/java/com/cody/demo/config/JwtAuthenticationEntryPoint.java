package com.cody.demo.config;

import com.cody.demo.dao.ResultCode;
import com.cody.demo.dao.ResultJson;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

/**
 * @File Name: com.cody.demo.config
 * @Author: WQL //作者及
 * @Date: 2019/4/2 16:55//完成日期
 * @Description: // 认证失败
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable
{

    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException
    {
        // 验证为登录状态会进入此方法，认证错误
        System.out.println("认证失败:" + e.getMessage());
        response.setStatus(200);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String body = ResultJson.failure(ResultCode.UNAUTHORIZED, e.getMessage()).toString();
        printWriter.write(body);
        printWriter.flush();

    }
}