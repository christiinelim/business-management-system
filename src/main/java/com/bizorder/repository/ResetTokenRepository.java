package com.bizorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizorder.model.ResetToken;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {
    ResetToken findByToken(String token);
}
