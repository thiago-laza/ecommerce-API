package com.ecommerce_api.order;

import java.time.LocalDate;
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

import com.ecommerce_api.customer.Customer;
import com.ecommerce_api.product.Product;





@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order){
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.saveOrder(order));
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders(){
        List<Order> orders = orderService.findAllOrders();
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id){
        Optional<Order> orders = orderService.findByIdOrder(id);
        return orders.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order newOrder){
        Optional<Order> OrderOptional = orderService.findByIdOrder(id);

        if (OrderOptional.isPresent()) {
            Order orderToUpdate = OrderOptional.get();

            orderToUpdate.setProducts(newOrder.getProducts());
            orderToUpdate.setTotalValue(newOrder.getTotalValue());
            orderToUpdate.setOrderDate(newOrder.getOrderDate());
            orderToUpdate.setCustomer(newOrder.getCustomer());
            orderToUpdate.setStatus(newOrder.getStatus());
            orderToUpdate.setDiscount(newOrder.getDiscount());
            orderToUpdate.setShippingAddress(newOrder.getShippingAddress());

            double totalValue = orderService.calcularValorTotal(orderToUpdate);
            double discount = orderService.calcularDesconto(totalValue);
            totalValue -= discount;
            orderToUpdate.setDiscount(discount);
            orderToUpdate.setTotalValue(totalValue);

            return ResponseEntity.status(HttpStatus.OK).body(orderService.saveOrder(orderToUpdate));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdOrder(@PathVariable Long id){
        orderService.deleteByIdOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByProduct")
    public ResponseEntity<List<Order>> getOrderByProduct(@RequestParam Product product){
        List<Order> orders = orderService.findOrderByProduct(product);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByTotalValue")
    public ResponseEntity<List<Order>> getOrderByTotalValue(@RequestParam Double totalValue){
        List<Order> orders = orderService.findOrderByTotalValue(totalValue);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByDate")
        public ResponseEntity<List<Order>> getOrderByDate(@RequestParam LocalDate orderDate){
    List<Order> orders = orderService.findOrderByOrderDate(orderDate);
    if (orders.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    return ResponseEntity.ok(orders);
}

    
    @GetMapping("/searchByCustomer")
    public ResponseEntity<List<Order>> getOrderByCustomer(@RequestParam Customer customer){
        List<Order> orders = orderService.findOrderByCustomer(customer);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByStatus")
    public ResponseEntity<List<Order>> getOrderByStatus(@RequestParam String status){
        List<Order> orders = orderService.findOrderByStatus(status);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByDiscount")
    public ResponseEntity<List<Order>> getOrderByDiscount(@RequestParam Double discount){
        List<Order> orders = orderService.findOrderByDiscount(discount);
        if (orders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/searchByShippingAddress")
    public ResponseEntity<List<Order>> getOrderByShippingAddress(@RequestParam String address){
        List<Order> oreders = orderService.findOrderByShippingAddress(address);
        if (oreders.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(oreders);
    }
    
}

