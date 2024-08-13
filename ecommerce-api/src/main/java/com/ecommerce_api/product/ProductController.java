package com.ecommerce_api.product;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.saveProduct(product));
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.findAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Optional<Product> product = productService.findByIdProduct(id);
        return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product newProduct){
        Optional<Product> ProductOptional = productService.findByIdProduct(id);

        if (ProductOptional.isPresent()) {
            Product productToUpdate = ProductOptional.get();

            productToUpdate.setName(newProduct.getName());
            productToUpdate.setPrice(newProduct.getPrice());
            productToUpdate.setDescription(newProduct.getDescription());
            productToUpdate.setCategory(newProduct.getCategory());
            productToUpdate.setStock(newProduct.getStock());

            return ResponseEntity.status(HttpStatus.OK).body(productService.saveProduct(productToUpdate));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdProduct(@PathVariable Long id){
        productService.deleteByProduct(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Product>> getProductByName(@RequestParam String name){
        List<Product> products = productService.findProductByName(name);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/searchByPrice")
    public ResponseEntity<List<Product>> getProductByPrice(@RequestParam Double price){
        List<Product> products = productService.findProductByPrice(price);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/searchByPriceRange")
    public ResponseEntity<List<Product>> getProductByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice){
        List<Product> products = productService.findProductsByPriceRange(minPrice, maxPrice);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }

    @GetMapping("/searchByCategory")
    public ResponseEntity<List<Product>> getProductByCategory(@RequestParam String category){
        List<Product> products = productService.findProductByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(products);
    }
}
