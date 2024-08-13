package com.ecommerce_api.order;

import java.time.LocalDateTime;
import java.util.List;

import com.ecommerce_api.customer.Customer;
import com.ecommerce_api.product.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private List<Product> products;

    @Column(nullable = false)
    private Double totalValue;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @ManyToOne
    private Customer customer;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private Double discount;

    @Column(nullable = false)
    private String shippingAddress;
}
