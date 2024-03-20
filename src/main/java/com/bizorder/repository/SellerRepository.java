package com.bizorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizorder.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Integer> {
    
}
