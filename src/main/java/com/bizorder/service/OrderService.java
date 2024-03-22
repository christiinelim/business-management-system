package com.bizorder.service;

import java.util.List;

import com.bizorder.model.Order;

public interface OrderService {
   
    public List<Order> getAllOrders();
    public Order getOrder(Integer orderId);
    public List<Order> getOrderByCustomer(Integer customerId);
    public String createOrder(Order order);
    public String updateOrder(Order order, Integer orderId);
    public String deleteOrder(Integer orderId);

}
