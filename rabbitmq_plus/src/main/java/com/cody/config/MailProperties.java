package com.cody.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @File Name: com.cody.config
 * @Author: WQL //作者及
 * @Date: 2019/6/10 14:15//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Configuration
@ConfigurationProperties(prefix = "spring.boot.mail.properties")
public class MailProperties
{
    private String host;

    private Integer port;

    private String userName;

    private String password;

    private String protocol;

    private String needAuth;

    private String sslClass;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getNeedAuth()
    {
        return needAuth;
    }

    public void setNeedAuth(String needAuth)
    {
        this.needAuth = needAuth;
    }

    public String getSslClass()
    {
        return sslClass;
    }

    public void setSslClass(String sslClass)
    {
        this.sslClass = sslClass;
    }
}