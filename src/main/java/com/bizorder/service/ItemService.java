package com.bizorder.service;

import java.util.List;

import com.bizorder.model.Item;

public interface ItemService {
   
    public List<Item> getAllItems();
    public Item getItem(Integer itemId);
    public String createItem(Item item);
    public String updateItem(Item item, Integer itemId);
    public String deleteItem(Integer itemId);

}
