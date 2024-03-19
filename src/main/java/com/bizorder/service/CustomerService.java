package com.bizorder.service;

import java.util.List;

import com.bizorder.model.Customer;

// To write the methods to have in business/service layer
public interface CustomerService {
    public String createCustomer(Customer customer);
    public String updateCustomer(Customer customer, Integer customerId);
    public String deleteCustomer(Integer customerId);
    public Customer getCustomer(Integer customerId);
    public List<Customer> getAllCustomers();
}
