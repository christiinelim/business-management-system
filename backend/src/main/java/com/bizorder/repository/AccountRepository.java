package com.bizorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizorder.model.Account;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByEmail(String email);
}