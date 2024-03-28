package com.bizorder.service;

import java.util.List;

import com.bizorder.model.Account;

public interface AccountService {
    public List<Account> getAllAccounts();
    public Account getAccount(Integer accountId);
    public String updateAccount(Account account, Integer accountId);
    public String deleteAccount(Integer accountId);
}
