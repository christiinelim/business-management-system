package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Account;
import com.bizorder.model.Customer;
import com.bizorder.model.Order;
import com.bizorder.repository.AccountRepository;
import com.bizorder.repository.CustomerRepository;
import com.bizorder.repository.OrderRepository;
import com.bizorder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final AccountRepository accountRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository, AccountRepository accountRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.accountRepository = accountRepository;
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
    public List<Order> getOrderByAccount(Integer accountId){
        if (orderRepository.findOrdersByAccountId(accountId).isEmpty()) {
            throw new SearchNotFoundException("Requested account does not exist");
        }
        return orderRepository.findOrdersByAccountId(accountId);
    }

    @Override
    public String createOrder(Order order){
        Integer customerId = order.getCustomer().getCustomerId();
        Integer accountId = order.getAccount().getAccountId();
        if (customerId == null || accountId == null) {
            return "Error: Customer ID or seller ID not indicated";
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Optional<Account> optionalAccount = accountRepository.findById(accountId);
            if (optionalAccount.isPresent()) {
                Customer foundCustomer = optionalCustomer.get();
                order.setCustomer(foundCustomer);
                Account foundAccount = optionalAccount.get();
                order.setAccount(foundAccount);
                order.setStatus("Pending");
                order.setPaid("No");
                Order newOrder = orderRepository.save(order);
                Integer orderId = newOrder.getOrderId();
                return "Success, inserted new order ID " + orderId;
            } else {
                throw new SearchNotFoundException("Account does not exist");
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
            existingOrder.setCollectionDate(order.getCollectionDate());
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
