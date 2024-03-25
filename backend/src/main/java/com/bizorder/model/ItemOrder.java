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
@Table (name = "items_orders")
public class ItemOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "purchaseId", insertable = false, updatable = false)
    private Integer purchaseId;

    @Column (name = "quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "orderId_fk", referencedColumnName = "orderId")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "itemId_fk", referencedColumnName = "itemId")
    private Item item;

    // Constructors
    public ItemOrder() {
    }

    public ItemOrder(Integer quantity, Order order, Item item) {
        this.quantity = quantity;
        this.order = order;
        this.item = item;
    }

    // Getters and setters
    public Integer getPurchaseId() {
        return purchaseId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
