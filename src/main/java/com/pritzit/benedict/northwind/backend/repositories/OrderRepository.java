package com.pritzit.benedict.northwind.backend.repositories;

import com.pritzit.benedict.northwind.backend.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
