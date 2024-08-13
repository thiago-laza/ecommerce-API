package com.ecommerce_api.order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce_api.customer.Customer;
import com.ecommerce_api.product.Product;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public Order saveOrder(Order order){
        double totalValue = calcularValorTotal(order);
        double discount = calcularDesconto(totalValue);
        totalValue -= discount;
        order.setDiscount(discount);
        order.setTotalValue(totalValue);
        return orderRepository.save(order);
    }

    public double calcularValorTotal(Order order){
        double total = 0.0;
        for(Product product : order.getProducts()){
            total += product.getPrice();
        }
        
        return total;
    }

    public double calcularDesconto(double totalValue){
        if (totalValue > 200.0) {
            return totalValue * 0.10;
        }else{
            return 0.0;
        }
    }

    public List<Order> findAllOrders(){
        return orderRepository.findAll();
    }

    public Optional<Order> findByIdOrder(Long id){
        return orderRepository.findById(id);
    }

    public void deleteByIdOrder(Long id){
        orderRepository.deleteById(id);
    }

    public List<Order> findOrderByProduct(Product product){
        return orderRepository.findByProducts(product);
    }

    public List<Order> findOrderByTotalValue(Double totalValue){
        return orderRepository.findByTotalValue(totalValue);
    }

    public List<Order> findOrderByOrderDate(LocalDate orderDate){
        return orderRepository.findByOrderDate(orderDate);
    }

    public List<Order> findOrderByCustomer(Customer customer){
        return orderRepository.findByCustomer(customer);
    }

    public List<Order> findOrderByStatus(String status){
        return orderRepository.findByStatus(status);
    }

    public List<Order> findOrderByDiscount(Double discount){
        return orderRepository.findByDiscount(discount);
    }

    public List<Order> findOrderByShippingAddress(String shippingAddress){
        return orderRepository.findByShippingAddress(shippingAddress);
    }
}
