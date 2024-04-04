package com.bizorder.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bizorder.model.ItemOrder;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Integer> {
    
    @Query("SELECT i FROM ItemOrder i WHERE i.order.orderId = :orderId")
    List<ItemOrder> findItemsByOrderId(Integer orderId);
}