package com.bizorder.service;

public interface EmailService {
    void sendResetEmail(String recipientEmail, String resetToken);
}
