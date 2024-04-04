package com.bizorder.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bizorder.model.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
    VerificationToken findByToken(Integer token);
    Optional<VerificationToken> findByEmail(String email);

    @Query("SELECT token FROM VerificationToken WHERE email = :email")
    Integer getVerificationTokenByEmail(@Param("email") String email);

    @Query("SELECT expiresAt FROM VerificationToken WHERE email = :email")
    Date getExpiresAtByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM VerificationToken WHERE token = :token")
    void deleteByVerificationToken(Integer token);

    @Transactional
    @Modifying
    @Query("DELETE FROM VerificationToken WHERE email = :email")
    void deleteByEmail(String email);
}