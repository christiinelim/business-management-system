package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Customer;
import com.bizorder.model.Order;
import com.bizorder.repository.CustomerRepository;
import com.bizorder.repository.OrderRepository;
import com.bizorder.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
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
    public List<Order> getOrderByCustomer(Integer customerId){
        if (orderRepository.findItemsByCustomerId(customerId).isEmpty()) {
            throw new SearchNotFoundException("Requested customer does not exist");
        }
        return orderRepository.findItemsByCustomerId(customerId);
    }

    @Override
    public String createOrder(Order order){
        // Retrieve the customerId from the order object
        Integer customerId = order.getCustomer().getCustomerId();
        if (customerId == null) {
            return "Error: Customer ID not indicated";
        }

        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer foundCustomer = optionalCustomer.get();
            order.setCustomer(foundCustomer);
            orderRepository.save(order);
            return "Success: Order created";
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
