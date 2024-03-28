package com.bizorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bizorder.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
    @Query("SELECT i FROM Order i WHERE i.account.accountId = :accountId")
    List<Order> findOrdersByAccountId(Integer accountId);
}
