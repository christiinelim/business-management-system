package com.bizorder.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bizorder.exception.CustomerNotFoundException;
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
        customerRepository.save(customer);
        return "Success";
    }

    @Override
    // public String updateCustomer(Customer customer){
    //     customerRepository.save(customer);
    //     return "Success";
    // }
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
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    // @Override
    // public String deleteCustomer(Integer customerId){
    //     customerRepository.deleteById(customerId);
    //     return "Success";
    // }

    @Override
    public String deleteCustomer(Integer customerId) {
        Optional<Customer> existingCustomerOptional = customerRepository.findById(customerId);

        if (existingCustomerOptional.isPresent()) {
            customerRepository.deleteById(customerId);
            return "Success";
        } else {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found");
        }
    }

    @Override
    public Customer getCustomer(Integer customerId){
        if (customerRepository.findById(customerId).isEmpty()){
            throw new CustomerNotFoundException("Requested customer does not exist");
        }
        return customerRepository.findById(customerId).get();
    }

    @Override
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}
