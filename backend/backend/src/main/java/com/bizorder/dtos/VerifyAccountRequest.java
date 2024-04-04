package com.bizorder.dtos;

public class VerifyAccountRequest {
    private String email;
    private Integer verificationToken;

    public VerifyAccountRequest() {
    }

    public VerifyAccountRequest(String email, Integer verificationToken) {
        this.email = email;
        this.verificationToken = verificationToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(Integer verificationToken) {
        this.verificationToken = verificationToken;
    }
}
