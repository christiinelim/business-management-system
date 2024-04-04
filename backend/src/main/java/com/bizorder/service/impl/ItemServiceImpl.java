package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Account;
import com.bizorder.model.Item;
import com.bizorder.repository.AccountRepository;
import com.bizorder.repository.ItemRepository;
import com.bizorder.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;
    private final AccountRepository accountRepository;

    public ItemServiceImpl(ItemRepository itemRepository, AccountRepository accountRepository) {
        this.itemRepository = itemRepository;
        this.accountRepository = accountRepository;
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
    public List<Item> getItemsByAccount(Integer accountId) {
        if (itemRepository.findItemsByAccountId(accountId).isEmpty()) {
            throw new SearchNotFoundException("Requested account and seller does not exist");
        }
        return itemRepository.findItemsByAccountId(accountId);
    }

    @Override
    public String createItem(Item item) {

        Integer accountId = item.getAccount().getAccountId();
        if (accountId == null) {
            return "Error: Account ID not indicated";
        }

        Optional<Account> optionalAccount = accountRepository.findById(item.getAccount().getAccountId());
        if (optionalAccount.isEmpty()) {
            throw new SearchNotFoundException("Account does not exist");
        }
        
        Account account = optionalAccount.get();
        item.setAccount(account);

        itemRepository.save(item);
        return "Success: Item created";
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
