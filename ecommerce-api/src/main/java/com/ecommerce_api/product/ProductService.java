package com.ecommerce_api.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findByIdProduct(Long id){
        return productRepository.findById(id);
    }

    public void deleteByProduct(Long id){
        productRepository.deleteById(id);
    }

    public List<Product> findProductByName(String name){
        return productRepository.findByName(name);
    }

    public List<Product> findProductByPrice(Double price){
        return productRepository.findByPrice(price);
    }

    public List<Product> findProductsByPriceRange(Double minPrice, Double maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> findProductByDescription(String description){
        return productRepository.findByDescription(description);
    }

    public List<Product> findProductByCategory(String category){
        return productRepository.findByCategory(category);
    }
}
