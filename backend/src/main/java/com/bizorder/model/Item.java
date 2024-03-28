package com.bizorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "itemId", insertable = false, updatable = false)
    private Integer itemId;

    @Column (name = "name")
    private String name;

    @Column (name = "cost")
    private double cost;

    @Column (name = "description")
    private String description;

    @Column (name = "reference")
    private String reference;

    @Column (name = "stock_on_hand")
    private Integer stock_on_hand;

    @ManyToOne
    @JoinColumn(name = "accountId_fk", referencedColumnName = "accountId")
    private Account account;

    // Default constructor
    public Item() {
    }

    // Constructor
    public Item(String name, double cost, String description, String reference, Integer stock_on_hand, Account account) {
        this.name = name;
        this.cost = cost;
        this.description = description;
        this.reference = reference;
        this.stock_on_hand = stock_on_hand;
        this.account = account;
    }

    // Getters and setters
    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public Integer getStock_on_hand() {
        return stock_on_hand;
    }

    public void setStock_on_hand(Integer stock_on_hand) {
        this.stock_on_hand = stock_on_hand;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    
}
