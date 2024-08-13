package com.ecommerce_api.customer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{
    List<Customer> findByName(String name);
    List<Customer> findByEmail(String email);
    List<Customer> findByPhone(String phone);
    List<Customer> findByAddress(String address);
}
