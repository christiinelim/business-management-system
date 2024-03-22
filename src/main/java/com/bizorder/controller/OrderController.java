package com.bizorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.model.Order;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    OrderService orderService;

    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }
    
    // Read
    @GetMapping
    public ResponseEntity<Object> getAllOrders() {
        try {
            return ResponseHandler.responseBuilder("Requested orders details retrieved", HttpStatus.OK, orderService.getAllOrders());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting order details: " + e.getMessage());
        }
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return ResponseHandler.responseBuilder("Requested order details retrieved", HttpStatus.OK, orderService.getOrder(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting item details: " + e.getMessage());
        }
    }

    // get all order by a customer
    @GetMapping("customer/{customerId}")
    public ResponseEntity<Object> getOrderByCustomer(@PathVariable("customerId") Integer customerId) {
        try {
            return ResponseHandler.responseBuilder("Requested customer order retrieved", HttpStatus.OK, orderService.getOrderByCustomer(customerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting customer's order details: " + e.getMessage());
        }
    }
    

    // Create
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        try {
            orderService.createOrder(order);
            return ResponseHandler.responseBuilder("Order created", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating order: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody Order order) {
        try {
            orderService.updateOrder(order, orderId);
            return ResponseHandler.responseBuilder("Item details updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating item details: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("orderId") Integer orderId) {
        try {
            orderService.deleteOrder(orderId);
            return ResponseHandler.responseBuilder("Order deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting order: " + e.getMessage());
        }
    }
}