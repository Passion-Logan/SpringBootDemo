package com.cody.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AnyUserDetailsService anyUserDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 匹配 "/" 路径，不需要权限即可访问
     * 匹配 "/user" 及其以下所有路径，都需要 "USER" 权限
     * 登录地址为 "/login"，登录成功默认跳转到页面 "/user"
     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
     * 默认启用 CSRF
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/");
        http.headers().frameOptions().sameOrigin();
    }

    /**
     * 添加 UserDetailsService， 实现自定义登录校验
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(anyUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
