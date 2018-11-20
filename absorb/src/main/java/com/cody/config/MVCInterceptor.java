package com.cody.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class MVCInterceptor implements HandlerInterceptor {

    static Logger logger = LoggerFactory.getLogger(MVCInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //测试用
        //logger.info("请求路径：{}", request.getRequestURI());

        if(request.getRequestURI().equals("/error")) {
            response.sendRedirect("index");
            return false;
        }
        return true;
    }
}
