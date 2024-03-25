package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Item;
import com.bizorder.model.Seller;
import com.bizorder.repository.ItemRepository;
import com.bizorder.repository.SellerRepository;
import com.bizorder.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

    ItemRepository itemRepository;
    SellerRepository sellerRepository;

    public ItemServiceImpl(ItemRepository itemRepository, SellerRepository sellerRepository) {
        this.itemRepository = itemRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item getItem(Integer itemId) {
        if (itemRepository.findById(itemId).isEmpty()){
            throw new SearchNotFoundException("Requested item does not exist");
        }
        return itemRepository.findById(itemId).get();
    }

    @Override
    public List<Item> getItemsBySeller(Integer sellerId) {
        if (itemRepository.findItemsBySellerId(sellerId).isEmpty()) {
            throw new SearchNotFoundException("Requested seller does not exist");
        }
        return itemRepository.findItemsBySellerId(sellerId);
    }

    @Override
    public String createItem(Item item) {
        
        // Retrieve the sellerId from the item object
        Integer sellerId = item.getSeller().getSellerId();
        if (sellerId == null) {
            return "Error: Seller ID not indicated";
        }

        Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
        if (optionalSeller.isPresent()) {
            Seller foundSeller = optionalSeller.get();
            item.setSeller(foundSeller);
            itemRepository.save(item);
            return "Success: Item created";
        } else {
            throw new SearchNotFoundException("Seller does not exist");
        }
    }


    @Override
    public String updateItem(Item item, Integer itemId) {
        Optional<Item> existingItemOptional = itemRepository.findById(itemId);
        
        if (existingItemOptional.isPresent()) {
            Item existingItem = existingItemOptional.get();
            existingItem.setName(item.getName());
            existingItem.setCost(item.getCost());
            existingItem.setDescription(item.getDescription());
            existingItem.setReference(item.getReference());
            existingItem.setStock_on_hand(item.getStock_on_hand());
    
            itemRepository.save(existingItem);
            
            return "Success";
        } else {
            throw new SearchNotFoundException("Item with ID " + itemId + " not found");
        }
    }

    @Override
    public String deleteItem(Integer itemId) {
        Optional<Item> existingItemOptional = itemRepository.findById(itemId);

        if (existingItemOptional.isPresent()){
            itemRepository.deleteById(itemId);
            return "Success";
        } else {
            throw new SearchNotFoundException("Item with ID " + itemId + " not found");
        }
    }

}
