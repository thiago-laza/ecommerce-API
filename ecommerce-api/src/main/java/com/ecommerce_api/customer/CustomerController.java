package com.ecommerce_api.customer;

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
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(customer));
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers(){
        List<Customer> customer = customerService.findAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id){
        Optional<Customer> customer = customerService.findByIdCustomer(id);
        return customer.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer newCustomer){
        Optional<Customer> CustomerOptional = customerService.findByIdCustomer(id);

        if (CustomerOptional.isPresent()) {
            Customer customerToUpdate = CustomerOptional.get();

            customerToUpdate.setName(newCustomer.getName());
            customerToUpdate.setEmail(newCustomer.getEmail());
            customerToUpdate.setPhone(newCustomer.getPhone());
            customerToUpdate.setAddress(newCustomer.getAddress());

            return ResponseEntity.status(HttpStatus.OK).body(customerService.saveCustomer(customerToUpdate));
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByCustomer(@PathVariable Long id){
        customerService.deleteByIdCustomer(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/searchByName")
    public ResponseEntity<List<Customer>> getCustomerByName(@RequestParam String name){
        List<Customer> customer = customerService.findCustomerByName(name);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/searchByEmail")
    public ResponseEntity<List<Customer>> getCustomerByEmail(@RequestParam String email){
        List<Customer> customer = customerService.findCustomerByEmail(email);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/searchByPhone")
    public ResponseEntity<List<Customer>> getCustomerByPhone(@RequestParam String phone){
        List<Customer> customer = customerService.findCustomerByPhone(phone);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }

    @GetMapping("/searchByAddress")
    public ResponseEntity<List<Customer>> getCustomerByAddress(@RequestParam String address){
        List<Customer> customer = customerService.findCustomerByAddress(address);
        if (customer.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(customer);
    }
}
