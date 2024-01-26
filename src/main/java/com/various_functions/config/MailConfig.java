package com.various_functions.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//@Configuration
//public class MailConfig {
//
//	@Bean
//    public JavaMailSender javaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost("smtp.naver.com");
//        mailSender.setPort(465);
//        mailSender.setUsername("나의메일주소");
//        mailSender.setPassword("메일비번");
//
//        Properties properties = new Properties();
//        properties.put("mail.smtp.auth", true);
//        properties.put("mail.smtp.starttls.enable", true);
//        properties.put("mail.smtps.checkserveridentity", true);
//        properties.put("mail.smtps.ssl.trust", "*");
//        properties.put("mail.debug", true);
//        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//        mailSender.setJavaMailProperties(properties);
//
//        return mailSender;
//    }
//}
