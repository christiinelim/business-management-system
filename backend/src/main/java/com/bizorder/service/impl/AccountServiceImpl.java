package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Account;
import com.bizorder.repository.AccountRepository;
import com.bizorder.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Account getAccount(Integer accountId) {
        if (accountRepository.findById(accountId).isEmpty()){
            throw new SearchNotFoundException("Requested account does not exist");
        }
        return accountRepository.findById(accountId).get();
    }

    @Override
    public String updateAccount(Account account, Integer accountId) {
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);
        
        if (existingAccountOptional.isPresent()) {
            Account existingAccount = existingAccountOptional.get();
            existingAccount.setEmail(account.getEmail());
            existingAccount.setPassword(passwordEncoder.encode(account.getPassword()));
            existingAccount.setName(account.getName());
            existingAccount.setContact(account.getContact());
            existingAccount.setInstagram(account.getInstagram());
            existingAccount.setTiktok(account.getTiktok());
            existingAccount.setCarousell(account.getCarousell());
    
            accountRepository.save(existingAccount);
            
            return "Success";
        } else {
            throw new SearchNotFoundException("Account with ID " + accountId + " not found");
        }
    }

    @Override
    public String deleteAccount(Integer accountId){
        Optional<Account> existingAccountOptional = accountRepository.findById(accountId);

        if (existingAccountOptional.isPresent()){
            accountRepository.deleteById(accountId);
            return "Success";
        } else {
            throw new SearchNotFoundException("Account with ID " + accountId + " not found");
        }
    }

}
