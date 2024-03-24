package com.bizorder.dtos;

public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;

    // Getter for email
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getPassword() {
        return password;
    }

    // Setter for password
    public void setPassword(String password) {
        this.password = password;
    }

    // Getter for fullName
    public String getFullName() {
        return fullName;
    }

    // Setter for fullName
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
