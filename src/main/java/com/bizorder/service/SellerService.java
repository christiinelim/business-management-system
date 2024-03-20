package com.bizorder.service;

import com.bizorder.model.Seller;

public interface SellerService {
    
    public Seller getSeller(Integer sellerId);
    public String createSeller(Seller seller);
    public String updateSeller(Seller seller, Integer sellerId);
    public String deleteSeller(Integer sellerId);

}
