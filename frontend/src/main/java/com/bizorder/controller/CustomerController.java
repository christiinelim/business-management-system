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

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Customer;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    // Read specific customer
    @GetMapping("{customerId}")
    public ResponseEntity<Object> getCustomer(@PathVariable("customerId") Integer customerId) {
        try {
            return ResponseHandler.responseBuilder("Requested customer details retrieved", HttpStatus.OK, customerService.getCustomer(customerId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting customer: " + e.getMessage());
        }
    }

    // Read all customers
    @GetMapping()
    public ResponseEntity<Object> getAllCustomers() {
        try {
            return ResponseHandler.responseBuilder("Requested customer list retrieved", HttpStatus.OK, customerService.getAllCustomers());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving customer list: " + e.getMessage());
        }
    }

    // Create new customer
    @PostMapping
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer){
        try {
            return ResponseHandler.responseBuilder("Customer created successfully", HttpStatus.OK, customerService.createCustomer(customer));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error creating customer: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{customerId}")
    public ResponseEntity<Object> updateCustomer(@PathVariable("customerId") Integer customerId, @RequestBody Customer customer) {
        try {
            return ResponseHandler.responseBuilder("Customer updated successfully", HttpStatus.OK, customerService.updateCustomer(customer, customerId));
        } catch (SearchNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating customer: " + e.getMessage());
        }
    }

    // // Delete
    // @DeleteMapping("{customerId}")
    // public String deleteCustomer(@PathVariable("customerId") Integer customerId){
    //     customerService.deleteCustomer(customerId);
    //     return "Customer deleted successfully";
    // }

    // Delete
    @DeleteMapping("{customerId}")
    public ResponseEntity<Object> deleteCustomer(@PathVariable("customerId") Integer customerId) {
        try {
            return ResponseHandler.responseBuilder("Customer deleted successfully", HttpStatus.OK, customerService.deleteCustomer(customerId));
        } catch (SearchNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting customer: " + e.getMessage());
        }
    }
}
