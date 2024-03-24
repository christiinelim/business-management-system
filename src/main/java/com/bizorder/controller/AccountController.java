package com.bizorder.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bizorder.model.Account;
import com.bizorder.service.AccountService;

import java.util.List;

@RequestMapping("/account")
@RestController
public class AccountController {
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/admin")
    public ResponseEntity<Account> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Account currentUser = (Account) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @GetMapping
    public ResponseEntity<List<Account>> allUsers() {
        List <Account> users = accountService.getAllAccounts();

        return ResponseEntity.ok(users);
    }
    
}
