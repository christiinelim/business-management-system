package com.bizorder.service;

import java.util.List;

import com.bizorder.model.Seller;

public interface SellerService {
   
    public List<Seller> getAllSellers();
    public Seller getSeller(Integer sellerId);
    public String createSeller(Seller seller);
    public String updateSeller(Seller seller, Integer sellerId);
    public String deleteSeller(Integer sellerId);

}
