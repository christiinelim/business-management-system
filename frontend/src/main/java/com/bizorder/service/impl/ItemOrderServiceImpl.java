package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Item;
import com.bizorder.model.ItemOrder;
import com.bizorder.model.Order;
import com.bizorder.repository.ItemOrderRepository;
import com.bizorder.repository.ItemRepository;
import com.bizorder.repository.OrderRepository;
import com.bizorder.service.ItemOrderService;

@Service
public class ItemOrderServiceImpl implements ItemOrderService {
    
    ItemOrderRepository itemOrderRepository;
    OrderRepository orderRepository;
    ItemRepository itemRepository;

    public ItemOrderServiceImpl(ItemOrderRepository itemOrderRepository, OrderRepository orderRepository, ItemRepository itemRepository) {
        this.itemOrderRepository = itemOrderRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemOrder getPurchase(Integer purchaseId){
        if (itemOrderRepository.findById(purchaseId).isEmpty()){
            throw new SearchNotFoundException("Requested purchase does not exist");
        }
        return itemOrderRepository.findById(purchaseId).get();
    }

    @Override
    public List<ItemOrder> getItemsByOrder(Integer orderId){
        if (itemOrderRepository.findItemsByOrderId(orderId).isEmpty()) {
            throw new SearchNotFoundException("Requested order does not exist");
        }
        return itemOrderRepository.findItemsByOrderId(orderId);
    }

    @Override
    public String createItemOrder(ItemOrder itemOrder){
        Integer itemId = itemOrder.getItem().getItemId();
        Integer orderId = itemOrder.getOrder().getOrderId();

        if (itemId == null || orderId == null) {
            return "Error: Item ID or Order ID not indicated";
        }

        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order foundOrder = optionalOrder.get();
            itemOrder.setOrder(foundOrder);

            Optional<Item> optionalItem = itemRepository.findById(itemId);
            if (optionalItem.isPresent()) {
                Item foundItem = optionalItem.get();
                itemOrder.setItem(foundItem);

                itemOrderRepository.save(itemOrder);
                return "Success: Purchase created";
            } else {
                throw new SearchNotFoundException("Item does not exist");
            } 
        } else {
            throw new SearchNotFoundException("Order does not exist");
        }
    }

    @Override
    public String updateItemOrder(ItemOrder itemOrder, Integer purchaseId){
        Optional<ItemOrder> existingItemOrderOptional = itemOrderRepository.findById(purchaseId);
        
        if (existingItemOrderOptional.isPresent()) {
            ItemOrder existingItemOrder = existingItemOrderOptional.get();
            existingItemOrder.setQuantity(itemOrder.getQuantity());
    
            itemOrderRepository.save(existingItemOrder);
            
            return "Success";
        } else {
            throw new SearchNotFoundException("Purchase with ID " + purchaseId + " not found");
        }
    }

    @Override
    public String deleteItemOrder(Integer purchaseId){
        Optional<ItemOrder> existingItemOrderOptional = itemOrderRepository.findById(purchaseId);

        if (existingItemOrderOptional.isPresent()){
            itemOrderRepository.deleteById(purchaseId);
            return "Success";
        } else {
            throw new SearchNotFoundException("Purchase with ID " + purchaseId + " not found");
        }
    }
}
