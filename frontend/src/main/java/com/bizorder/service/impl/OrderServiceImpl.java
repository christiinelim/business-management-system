package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Customer;
import com.bizorder.model.Order;
import com.bizorder.model.Seller;
import com.bizorder.repository.CustomerRepository;
import com.bizorder.repository.OrderRepository;
import com.bizorder.repository.SellerRepository;
import com.bizorder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;
    SellerRepository sellerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, SellerRepository sellerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.sellerRepository = sellerRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrder(Integer orderId){
        if (orderRepository.findById(orderId).isEmpty()){
            throw new SearchNotFoundException("Requested order does not exist");
        }
        return orderRepository.findById(orderId).get();
    }

    @Override
    public List<Order> getOrderBySeller(Integer sellerId){
        if (orderRepository.findOrdersBySellerId(sellerId).isEmpty()) {
            throw new SearchNotFoundException("Requested seller does not exist");
        }
        return orderRepository.findOrdersBySellerId(sellerId);
    }

    @Override
    public String createOrder(Order order){
        Integer customerId = order.getCustomer().getCustomerId();
        Integer sellerId = order.getSeller().getSellerId();
        if (customerId == null || sellerId == null) {
            return "Error: Customer ID or seller ID not indicated";
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<Seller> optionalSeller = sellerRepository.findById(sellerId);
            if (optionalCustomer.isPresent()) {
                Customer foundCustomer = optionalCustomer.get();
                order.setCustomer(foundCustomer);
                Seller foundSeller = optionalSeller.get();
                order.setSeller(foundSeller);
                Order newOrder = orderRepository.save(order);
                Integer orderId = newOrder.getOrderId();
                return "Success, inserted new order ID " + orderId;
            } else {
                throw new SearchNotFoundException("Seller does not exist");
            }   
        } else {
            throw new SearchNotFoundException("Customer does not exist");
        }
    }

    @Override
    public String updateOrder(Order order, Integer orderId){
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);
        
        if (existingOrderOptional.isPresent()) {
            Order existingOrder = existingOrderOptional.get();
            // existingOrder.setCollectionDate(order.getCollectionDate());
            existingOrder.setNote(order.getNote());
            existingOrder.setPaid(order.getPaid());
            existingOrder.setStatus(order.getStatus());
    
            orderRepository.save(existingOrder);
            
            return "Success";
        } else {
            throw new SearchNotFoundException("Order with ID " + orderId + " not found");
        }
    }

    @Override
    public String deleteOrder(Integer orderId){
        Optional<Order> existingOrderOptional = orderRepository.findById(orderId);

        if (existingOrderOptional.isPresent()){
            orderRepository.deleteById(orderId);
            return "Success";
        } else {
            throw new SearchNotFoundException("Order with ID " + orderId + " not found");
        }
    }
}
