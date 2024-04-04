package com.bizorder.service;

import java.util.List;

import com.bizorder.model.ItemOrder;

public interface ItemOrderService {

    public ItemOrder getPurchase(Integer purchaseId);
    public List<ItemOrder> getItemsByOrder(Integer orderId);
    public String createItemOrder(ItemOrder itemOrder);
    public String updateItemOrder(ItemOrder itemOrder, Integer purchaseId);
    public String deleteItemOrder(Integer purchaseId);

}
