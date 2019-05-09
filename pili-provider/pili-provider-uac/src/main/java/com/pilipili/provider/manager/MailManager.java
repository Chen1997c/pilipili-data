package com.pilipili.provider.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 描述： 邮件业务
 *
 * @author ChenJianChuan
 * @date 2019/4/5　17:10
 */
@Service
public class MailManager {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     *
     * @param title 标题
     * @param content 内容
     * @param email 目标邮箱地址
     */
    @Async
    public void sendMail(String title, String content, String email) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setSubject(title);
        message.setText(content);
        message.setTo(email);
        mailSender.send(message);
    }
}
