package com.ecommerce_api.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByName(String name);
    List<Product> findByPrice(Double price);
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);
    List<Product> findByDescription(String description);
    List<Product> findByCategory(String category);
}
