package com.pritzit.benedict.northwind.backend.services;

import com.pritzit.benedict.northwind.backend.entities.Customer;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerService {
    // Wird durch Spring eingebunden
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll(){
        log.info("Get All Customers called");
        List<Customer> all = customerRepository.findAll();
        log.info("Find All Customers returned {} customers", all.size());
        return all;
    }

    public Customer getById(String id){
        log.info("Getting customer by id ({}) from DB", id);

        Optional<Customer> customerOptional = customerRepository.findById(id);
        if(customerOptional.isEmpty()){
            log.warn("Customer for id({}) not found", id);
            throw new ResourceNotFoundException("Customer for id " + id + " not found");
        }

        log.info("Found customer with id {} - Name: {}", id, customerOptional.get().getCompanyName());
        return customerOptional.get();
    }
}
