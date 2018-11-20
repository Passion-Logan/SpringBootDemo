package com.cody.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MVCConfig implements WebMvcConfigurer {

    @Autowired
    private MVCInterceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = new String[]{"/", "/index", "/static/**" };

        registry.addInterceptor(new MVCInterceptor()).addPathPatterns("/**").excludePathPatterns(excludes);
    }
}
