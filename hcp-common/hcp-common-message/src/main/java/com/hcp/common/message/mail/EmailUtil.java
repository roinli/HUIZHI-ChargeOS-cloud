package com.hcp.common.message.mail;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class EmailUtil {

    @Value("${spring.mail.username}")
    private String emailFrom;


    @Autowired
    private JavaMailSender mailSender;

    public  void sendSimpleMail( String subject, String content,String maill){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(emailFrom); // 邮件发送者
            message.setTo(maill); // 邮件接受者
            message.setSubject(subject); // 主题
            message.setText(content); // 内容
            mailSender.send(message);
        }catch (MailException e){
            log.info("邮件发送失败:"+e.getMessage());
        }
    }


}
