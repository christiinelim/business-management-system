package com.bizorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @Column (name = "contact")
    private String contact;

    @Column (name = "instagram")
    private String instagram;

    @Column (name = "tiktok")
    private String tiktok;

    @Column (name = "carousell")
    private String carousell;

    @OneToOne
    @JoinColumn(name = "accountId_fk", referencedColumnName = "accountId")
    private Account account;

    // Default constructor
    public Seller() {
    }

    // Constructor
    public Seller(String name, String contact, String instagram, String tiktok, String carousell, Account account) {
        this.name = name;
        this.contact = contact;
        this.instagram = instagram;
        this.tiktok = tiktok;
        this.carousell = carousell;
        this.account = account;
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

    public Account getAccount() {
        return account;
    }

    public Seller setAccount(Account account) {
        this.account = account;
        return this;
    }
}

