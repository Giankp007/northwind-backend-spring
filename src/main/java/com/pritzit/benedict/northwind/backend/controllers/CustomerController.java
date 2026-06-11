package com.pritzit.benedict.northwind.backend.controllers;

import com.pritzit.benedict.northwind.backend.entities.Customer;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.services.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping
    public List<Customer> getAllCustomers() {
        log.info("Fetch All Customers called");
        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id){
        log.info("Fetch Customer by id ({}) called", id);
        try{
            Customer customer = customerService.getById(id);
            return ResponseEntity.ok(customer);
        } catch (ResourceNotFoundException e){
            log.warn("Customer not found: ", e);
            return ResponseEntity.notFound().build();
        }
        finally {
            log.info("Customer retrieval completed for id: {}", id);
        }

    }
}
