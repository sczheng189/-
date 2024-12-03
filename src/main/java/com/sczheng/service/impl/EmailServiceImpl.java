package com.sczheng.service.impl;

import com.sczheng.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService{
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public void sendDeliveryNotification(String to, String orderInfo) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo(to);
            message.setSubject("订单发货通知");
            message.setText("您的订单已发货！\n订单信息：" + orderInfo);

            javaMailSender.send(message);
            log.info("发货通知邮件已发送至：{}", to);
        } catch (Exception e) {
            log.error("发送邮件失败：", e);
        }
    }
}
