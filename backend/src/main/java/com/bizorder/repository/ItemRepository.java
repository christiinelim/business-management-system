package com.bizorder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bizorder.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
    @Query("SELECT i FROM Item i WHERE i.account.accountId = :accountId")
    List<Item> findItemsByAccountId(Integer accountId);
}
