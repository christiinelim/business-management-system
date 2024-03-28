package com.bizorder.dtos;

public class SellerDto {
    private Integer accountId;
    private String name;
    private String contact;
    private String instagram;
    private String tiktok;
    private String carousell;

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
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
}
