package com.bizorder.service;

public interface EmailService {
    void sendResetEmail(String recipientEmail, String resetToken);
    void sendVerificationEmail(String recipientEmail, Integer verificationToken);
}
