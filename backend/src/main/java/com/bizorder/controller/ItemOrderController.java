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

import com.bizorder.api.ItemOrderApi;
import com.bizorder.model.ItemOrder;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.ItemOrderService;

@RestController
@RequestMapping("/api/purchase")
public class ItemOrderController implements ItemOrderApi {

    ItemOrderService itemOrderService;

    public ItemOrderController(ItemOrderService itemOrderService){
        this.itemOrderService = itemOrderService;
    }
    
    // Read
    @GetMapping("{purchaseId}")
    public ResponseEntity<Object> getPurchase(@PathVariable("purchaseId") Integer purchaseId) {
        try {
            return ResponseHandler.responseBuilder("Requested purchase details retrieved", HttpStatus.OK, itemOrderService.getPurchase(purchaseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("order/{orderId}")
    public ResponseEntity<Object> getItemsByOrder(@PathVariable("orderId") Integer orderId) {
        try {
            return ResponseHandler.responseBuilder("Requested order details retrieved", HttpStatus.OK, itemOrderService.getItemsByOrder(orderId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
    // Create
    @PostMapping
    public ResponseEntity<Object> createItemOrder(@RequestBody ItemOrder itemOrder) {
        try {
            return ResponseHandler.responseBuilder("Purchase created", HttpStatus.OK, itemOrderService.createItemOrder(itemOrder));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{purchaseId}")
    public ResponseEntity<Object> updateItemOrder(@PathVariable("purchaseId") Integer purchaseId, @RequestBody ItemOrder itemOrder) {
        try {
            return ResponseHandler.responseBuilder("Purchase details updated", HttpStatus.OK, itemOrderService.updateItemOrder(itemOrder, purchaseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{purchaseId}")
    public ResponseEntity<Object> deleteItemOrder(@PathVariable("purchaseId") Integer purchaseId) {
        try {
            return ResponseHandler.responseBuilder("Item deleted", HttpStatus.OK, itemOrderService.deleteItemOrder(purchaseId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
