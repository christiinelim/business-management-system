package com.bizorder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.model.Seller;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.SellerService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/seller")
public class SellerController {

    SellerService sellerService;

    public SellerController(SellerService sellerService){
        this.sellerService = sellerService;
    }
    
    // Read
    @GetMapping
    public ResponseEntity<Object> getAllSellers() {
        try {
            return ResponseHandler.responseBuilder("Requested seller details retrieved", HttpStatus.OK, sellerService.getAllSellers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting seller details: " + e.getMessage());
        }
    }

    @GetMapping("{sellerId}")
    public ResponseEntity<Object> getSeller(@PathVariable("sellerId") Integer sellerId) {
        try {
            return ResponseHandler.responseBuilder("Requested seller details retrieved", HttpStatus.OK, sellerService.getSeller(sellerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting seller details: " + e.getMessage());
        }
    }

    // Create
    @PostMapping
    public ResponseEntity<Object> createSeller(@RequestBody Seller seller) {
        try {
            sellerService.createSeller(seller);
            return ResponseHandler.responseBuilder("Seller created", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating seller: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{sellerId}")
    public ResponseEntity<Object> updateSeller(@PathVariable("sellerId") Integer sellerId, @RequestBody Seller seller) {
        try {
            sellerService.updateSeller(seller, sellerId);
            return ResponseHandler.responseBuilder("Seller details updated", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating seller details: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{sellerId}")
    public ResponseEntity<Object> deleteSeller(@PathVariable("sellerId") Integer sellerId) {
        try {
            sellerService.deleteSeller(sellerId);
            return ResponseHandler.responseBuilder("Seller deleted", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting seller information: " + e.getMessage());
        }
    }
    
}
