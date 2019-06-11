package com.cody.service;

import com.cody.config.MailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * @File Name: com.cody.service
 * @Author: WQL //作者及
 * @Date: 2019/6/10 17:24//完成日期
 * @Description: // 描述
 * @Version: v0.0.1 // 版本信息
 * @Function List: // 主要函数及其功能
 * @Others: // 其它内容的说明
 * @History: // 历史修改记录
 */
@Service
public class MailService
{
    @Autowired
    private MailProperties mailProperties;

    @Autowired
    private Environment env;

    public void sendEmail() throws Exception{
        Properties properties = new Properties();
        properties.setProperty("mail.host", mailProperties.getHost());
        properties.setProperty("mail.transport.protocol", mailProperties.getProtocol());
        properties.setProperty("mail.smtp.port", mailProperties.getPort()+"");


        /*Session session = Session.getDefaultInstance(properties);
        session.setDebug(true);*/ //第一种写法

        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mailProperties.getUserName(),mailProperties.getPassword());
            }
        };
        Session session = Session.getInstance(properties,auth); //第二种写法

        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(env.getProperty("mail.from"));
        mimeMessage.setSubject(env.getProperty("mail.subject"));
        mimeMessage.setContent(env.getProperty("mail.content"), "text/html;charset=utf-8");

        //TODO：批量发送多个收件人
        String arr[] = env.getProperty("mail.to").split(",");
        Address[] addresses = new Address[arr.length];
        for(int i = 0;i<arr.length;i++){
            addresses[i] = new InternetAddress(arr[i]);
        }
        mimeMessage.addRecipients(Message.RecipientType.TO, addresses);

        //TODO：只发送一个收件人
        //mimeMessage.addRecipients(Message.RecipientType.TO, "1974544863@qq.com");
        //mimeMessage.addRecipients(Message.RecipientType.CC, "linsenzhong@126.com");

        Transport transport = session.getTransport();
        transport.connect(mailProperties.getHost(), mailProperties.getUserName(), mailProperties.getPassword());
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
        transport.close();
    }
}