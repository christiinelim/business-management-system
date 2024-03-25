package com.bizorder.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bizorder.model.Account;
import com.bizorder.repository.AccountRepository;
import com.bizorder.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository userRepository;

    public AccountServiceImpl(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<Account> getAllAccounts() {
        List<Account> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }


}
