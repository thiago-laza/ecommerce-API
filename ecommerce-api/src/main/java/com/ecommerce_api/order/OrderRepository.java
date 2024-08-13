package com.ecommerce_api.order;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce_api.customer.Customer;
import com.ecommerce_api.product.Product;

public interface OrderRepository extends JpaRepository<Order, Long>{
    List<Order> findByProducts(Product product);
    List<Order> findByTotalValue(Double totalValue);
    List<Order> findByOrderDate(LocalDate orderDate);
    List<Order> findByCustomer(Customer customer);
    List<Order> findByStatus(String status);
    List<Order> findByDiscount(Double discount);
    List<Order> findByShippingAddress(String shippingAddress);
}
