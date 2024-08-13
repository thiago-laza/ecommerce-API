package com.ecommerce_api.customer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer saveCustomer(Customer customer){
        return customerRepository.save(customer);
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findByIdCustomer(Long id){
        return customerRepository.findById(id);
    }

    public void deleteByIdCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public List<Customer> findCustomerByName(String name){
        return customerRepository.findByName(name);
    }

    public List<Customer> findCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }

    public List<Customer> findCustomerByPhone(String phone){
        return customerRepository.findByPhone(phone);
    }

    public List<Customer> findCustomerByAddress(String address){
        return customerRepository.findByAddress(address);
    }
}
