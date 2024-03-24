package com.bizorder.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bizorder.model.ResetToken;

public interface ResetTokenRepository extends JpaRepository<ResetToken, Integer> {
    ResetToken findByToken(String token);
    Optional<ResetToken> findByEmail(String email);

    @Query("SELECT rt.token FROM ResetToken rt WHERE rt.email = :email")
    String getHashedTokenByEmail(@Param("email") String email);

    @Query("SELECT rt.expiresAt FROM ResetToken rt WHERE rt.email = :email")
    Date getExpiresAtByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("DELETE FROM ResetToken rt WHERE rt.token = :hashedToken")
    void deleteByHashedToken(String hashedToken);

}
