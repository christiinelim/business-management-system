package com.bizorder.dtos;

public class ResetPasswordRequest {
    private String resetToken;
    private String newPassword;
    private String email;

    public ResetPasswordRequest() {
    }

    public ResetPasswordRequest(String resetToken, String newPassword, String email) {
        this.resetToken = resetToken;
        this.newPassword = newPassword;
        this.email = email;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


