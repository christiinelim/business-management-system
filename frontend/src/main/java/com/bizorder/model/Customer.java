package com.bizorder.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table (name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customerId", insertable = false, updatable = false)
    private Integer customerId;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "collection_method")
    private String collection_method;

    // Default constructor
    public Customer() {
    }

    // Constructor
    public Customer(String name, String contact, String address, String payment_method, String collection_method) {
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.payment_method = payment_method;
        this.collection_method = collection_method;
    }

    // Getters and setters for customerId
    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    // Getters and setters for name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and setters for contact
    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    // Getters and setters for address
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    // Getters and setters for payment_method
    public String getPaymentMethod() {
        return payment_method;
    }

    public void setPaymentMethod(String payment_method) {
        this.payment_method = payment_method;
    }

    // Getters and setters for collection_method
    public String getCollectionMethod() {
        return collection_method;
    }

    public void setCollectionMethod(String collection_method) {
        this.collection_method = collection_method;
    }

}
