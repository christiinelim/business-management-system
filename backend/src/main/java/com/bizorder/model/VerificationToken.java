package com.bizorder.model;

import java.util.Calendar;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "verification_tokens")
public class VerificationToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "tokenId", insertable = false, updatable = false)
    private Integer tokenId;

    @Column(nullable = false, unique = true)
    private Integer token;

    @Column(nullable = false)
    private String email;

    @CreationTimestamp
    @Column(updatable = false, name = "created_at")
    private Date createdAt;

    @Column(nullable = false, name = "expires_at")
    private Date expiresAt;

    // Constructors
    public VerificationToken() {
    }

    public VerificationToken(Integer token, String email) {
        this.token = token;
        this.email = email;
        this.createdAt = new Date(); 

        // expiresAt = createdAt + 30 minutes
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.createdAt);
        calendar.add(Calendar.MINUTE, 30);
        this.expiresAt = calendar.getTime();
    }

    // Getters and Setters
    public Integer getTokenId() {
        return tokenId;
    }

    public void setTokenId(Integer tokenId) {
        this.tokenId = tokenId;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }
}
