package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.SearchNotFoundException;
import com.bizorder.model.Customer;
import com.bizorder.repository.CustomerRepository;
import com.bizorder.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{

    CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    @Override
    public String createCustomer(Customer customer){
        Customer newCustomer = customerRepository.save(customer);
        Integer customerId = newCustomer.getCustomerId();
        return "Success, inserted new customer ID " + customerId;
    }

    @Override
    public String updateCustomer(Customer customer, Integer customerId) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);
        
        if (existingCustomerOptional.isPresent()) {
            Customer existingCustomer = existingCustomerOptional.get();
            existingCustomer.setName(customer.getName());
            existingCustomer.setContact(customer.getContact());
            existingCustomer.setAddress(customer.getAddress());
            existingCustomer.setPaymentMethod(customer.getPaymentMethod());
            existingCustomer.setCollectionMethod(customer.getCollectionMethod());
    
            customerRepository.save(existingCustomer);
            
            return "Success";
        } else {
            throw new SearchNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    @Override
    public String deleteCustomer(Integer customerId) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Success";
        } else {
            throw new SearchNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    @Override
    public Customer getCustomer(Integer customerId){
        if (customerRepository.findById(customerId).isEmpty()){
            throw new SearchNotFoundException("Requested customer does not exist");
        }
        return customerRepository.findById(customerId).get();
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
