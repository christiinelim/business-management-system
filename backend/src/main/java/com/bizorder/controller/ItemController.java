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

import com.bizorder.model.Item;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.ItemService;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    ItemService itemService;

    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }
    
    // Read
    @GetMapping
    public ResponseEntity<Object> getAllItems() {
        try {
            return ResponseHandler.responseBuilder("Requested items details retrieved", HttpStatus.OK, itemService.getAllItems());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("{itemId}")
    public ResponseEntity<Object> getItem(@PathVariable("itemId") Integer itemId) {
        try {
            return ResponseHandler.responseBuilder("Requested item details retrieved", HttpStatus.OK, itemService.getItem(itemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // get all items by a seller
    @GetMapping("account/{accountId}")
    public ResponseEntity<Object> getItemsBySeller(@PathVariable("accountId") Integer accountId) {
        try {
            return ResponseHandler.responseBuilder("Requested seller items retrieved", HttpStatus.OK, itemService.getItemsByAccount(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    

    // Create
    @PostMapping
    public ResponseEntity<Object> createSeller(@RequestBody Item item) {
        try {
            return ResponseHandler.responseBuilder("Item created", HttpStatus.OK, itemService.createItem(item));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{itemId}")
    public ResponseEntity<Object> updateSeller(@PathVariable("itemId") Integer itemId, @RequestBody Item item) {
        try {
            return ResponseHandler.responseBuilder("Item details updated", HttpStatus.OK, itemService.updateItem(item, itemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{itemId}")
    public ResponseEntity<Object> deleteItem(@PathVariable("itemId") Integer itemId) {
        try {
            return ResponseHandler.responseBuilder("Item deleted", HttpStatus.OK, itemService.deleteItem(itemId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
}
