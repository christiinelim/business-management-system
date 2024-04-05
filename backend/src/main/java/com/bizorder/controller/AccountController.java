package com.bizorder.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.api.AccountApi;
import com.bizorder.model.Account;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.AccountService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/account")
public class AccountController implements AccountApi {

    AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }
    
    // Read
    @GetMapping
    public ResponseEntity<Object> getAllAccounts() {
        try {
            return ResponseHandler.responseBuilder("Requested account details retrieved", HttpStatus.OK, accountService.getAllAccounts());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    @GetMapping("{accountId}")
    public ResponseEntity<Object> getAccount(@PathVariable("accountId") Integer accountId) {
        try {
            return ResponseHandler.responseBuilder("Requested account details retrieved", HttpStatus.OK, accountService.getAccount(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }

    // Update
    @PutMapping("{accountId}")
    public ResponseEntity<Object> updateAccount(@PathVariable("accountId") Integer accountId, @RequestBody Account account) {
        try {
            return ResponseHandler.responseBuilder("Account details updated", HttpStatus.OK, accountService.updateAccount(account, accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
  

    // Delete
    @DeleteMapping("{accountId}")
    public ResponseEntity<Object> deleteAccount(@PathVariable("accountId") Integer accountId) {
        try {
            return ResponseHandler.responseBuilder("Account deleted", HttpStatus.OK, accountService.deleteAccount(accountId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    
}
