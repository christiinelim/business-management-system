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

import com.bizorder.api.OrderApi;
import com.bizorder.model.Order;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.OrderService;

@RestController
@RequestMapping("/api/order")
public class OrderController implements OrderApi {

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
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("{orderId}")
    public ResponseEntity<Object> getOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return ResponseHandler.responseBuilder("Requested order details retrieved", HttpStatus.OK, orderService.getOrder(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // get all order by a customer
    @GetMapping("account/{accountId}")
    public ResponseEntity<Object> getOrderByAccount(@PathVariable("accountId") Integer accountId) {
        try {
            return ResponseHandler.responseBuilder("Requested orders retrieved", HttpStatus.OK, orderService.getOrderByAccount(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    

    // Create
    @PostMapping
    public ResponseEntity<Object> createOrder(@RequestBody Order order) {
        try {
            return ResponseHandler.responseBuilder("Order created", HttpStatus.OK, orderService.createOrder(order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{orderId}")
    public ResponseEntity<Object> updateOrder(@PathVariable("orderId") Integer orderId, @RequestBody Order order) {
        try {
            return ResponseHandler.responseBuilder("Item details updated", HttpStatus.OK,orderService.updateOrder(order, orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{orderId}")
    public ResponseEntity<Object> deleteOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return ResponseHandler.responseBuilder("Order deleted", HttpStatus.OK, orderService.deleteOrder(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}