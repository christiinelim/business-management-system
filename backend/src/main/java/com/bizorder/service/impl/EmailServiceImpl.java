package com.bizorder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bizorder.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendResetEmail(String recipientEmail, String resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bizordermanager@gmail.com");
        message.setTo(recipientEmail);
        message.setSubject("Password Reset");
        message.setText("Your password reset token is: " + resetToken);
        try {
            javaMailSender.send(message);
        } catch (Exception e){
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace(); 
        }
    }

    @Override
    public void sendVerificationEmail(String recipientEmail, Integer resetToken) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("bizordermanager@gmail.com");
        message.setTo(recipientEmail);
        message.setSubject("Verify you BizOrder Account");
        message.setText("Your verification token is: " + resetToken);
        try {
            javaMailSender.send(message);
        } catch (Exception e){
            System.out.println("Error sending email: " + e.getMessage());
            e.printStackTrace(); 
        }
    }
}
