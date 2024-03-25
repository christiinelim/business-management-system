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
@Table (name = "orders")
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "orderId", insertable = false, updatable = false)
    private Integer orderId;

    // @DateTimeFormat(pattern = "yyyy-MM-dd")
    // @Column(name = "collection_date")
    // private LocalDate collection_date;

    @Column (name = "note")
    private String note;

    @Column (name = "paid")
    private String paid;

    @Column (name = "status")
    private String status;

    @ManyToOne
    @JoinColumn(name = "customerId_fk", referencedColumnName = "customerId")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "sellerId_fk", referencedColumnName = "sellerId")
    private Seller seller;

    // Empty Constructor
    public Order() {
    }

    // Constructor
    // public Order(LocalDate collection_date, String note, String paid, String status, Customer customer) {
    //     this.collection_date = collection_date;
    //     this.note = note;
    //     this.paid = paid;
    //     this.status = status;
    //     this.customer = customer;
    // }

    public Order(String note, String paid, String status, Customer customer, Seller seller) {
        this.note = note;
        this.paid = paid;
        this.status = status;
        this.customer = customer;
        this.seller = seller;
    }

    // Getters and setters
    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
