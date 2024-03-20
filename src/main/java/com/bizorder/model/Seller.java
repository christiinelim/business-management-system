package com.bizorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "sellers")
public class Seller {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "sellerId", insertable = false, updatable = false)
    private Integer sellerId;

    @Column (name = "name")
    private String name;

    @Column (name = "email")
    private String email;

    @Column (name = "password")
    private String password;

    @Column (name = "contact")
    private String contact;

    @Column (name = "instagram")
    private String instagram;

    @Column (name = "tiktok")
    private String tiktok;

    @Column (name = "carousell")
    private String carousell;

    // Empty constructor
    public Seller() {
    }

    // Constructor
    public Seller(String name, String email, String password, String contact, String instagram, String tiktok, String carousell) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.carousell = carousell;
    }

    // Getter and Setter
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getTiktok() {
        return tiktok;
    }

    public void setTiktok(String tiktok) {
        this.tiktok = tiktok;
    }

    public String getCarousell() {
        return carousell;
    }

    public void setCarousell(String carousell) {
        this.carousell = carousell;
    }
}

