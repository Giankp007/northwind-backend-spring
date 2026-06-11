package com.pritzit.benedict.northwind.backend.repositories;

import com.pritzit.benedict.northwind.backend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}