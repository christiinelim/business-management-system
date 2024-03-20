package com.bizorder.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.CustomerNotFoundException;
import com.bizorder.model.Seller;
import com.bizorder.repository.SellerRepository;
import com.bizorder.service.SellerService;

@Service
public class SellerServiceImpl implements SellerService{
    
    SellerRepository sellerRepository;

    public SellerServiceImpl(SellerRepository sellerRepository){
        this.sellerRepository = sellerRepository;
    }

    @Override
    public Seller getSeller(Integer sellerId) {
        if (sellerRepository.findById(sellerId).isEmpty()){
            throw new CustomerNotFoundException("Requested seller does not exist");
        }
        return sellerRepository.findById(sellerId).get();
    }

    @Override
    public String createSeller(Seller seller) {
        sellerRepository.save(seller);
        return "Success";
    }

    @Override
    public String updateSeller(Seller seller, Integer sellerId) {
         Optional<Seller> existingSellerOptional = sellerRepository.findById(sellerId);
        
        if (existingSellerOptional.isPresent()) {
            Seller existingSeller = existingSellerOptional.get();
            existingSeller.setName(seller.getName());
            existingSeller.setEmail(seller.getEmail());
            existingSeller.setPassword(seller.getPassword());
            existingSeller.setEmail(seller.getEmail());
            existingSeller.setContact(seller.getContact());
            existingSeller.setInstagram(seller.getInstagram());
            existingSeller.setTiktok(seller.getTiktok());
            existingSeller.setCarousell(seller.getCarousell());
    
            sellerRepository.save(existingSeller);
            
            return "Success";
        } else {
            throw new CustomerNotFoundException("Seller with ID " + sellerId + " not found");
        }
    }

    @Override
    public String deleteSeller(Integer sellerId){
        Optional<Seller> existingSellerOptional = sellerRepository.findById(sellerId);

        if (existingSellerOptional.isPresent()){
            sellerRepository.deleteById(sellerId);
            return "Success";
        } else {
            throw new CustomerNotFoundException("Seller with ID " + sellerId + " not found");
        }
    }
    
}
