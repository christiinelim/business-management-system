package com.bizorder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bizorder.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
    
}
