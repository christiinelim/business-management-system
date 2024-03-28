package com.bizorder.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.model.Account;
import com.bizorder.response.ResponseHandler;
import com.bizorder.service.AccountService;

import java.util.List;

@RequestMapping("/api/account")
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<Object> authenticatedUser() {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            Account currentUser = (Account) authentication.getPrincipal();
            return ResponseHandler.responseBuilder("Accounts retrieved", HttpStatus.OK, currentUser);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving accounts: " + e.getMessage());
        }
    }

    @GetMapping("/admin")
    public ResponseEntity<Object> allUsers() {
        try {
            List <Account> users = accountService.getAllAccounts();
            return ResponseHandler.responseBuilder("User account retrieved", HttpStatus.OK, users);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error retrieving user details: " + e.getMessage());
        }
    }
    
}
