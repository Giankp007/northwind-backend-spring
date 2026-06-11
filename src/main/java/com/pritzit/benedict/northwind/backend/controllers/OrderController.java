package com.pritzit.benedict.northwind.backend.controllers;

import com.pritzit.benedict.northwind.backend.dtos.OrderDto;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        log.info("Endpoint [GET /orders] called");
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Integer id) {
        log.info("Endpoint [GET /orders/{}] called", id);

        try {
            OrderDto order = orderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (ResourceNotFoundException e) {
            log.warn(e.getMessage());
            return ResponseEntity.notFound().build();
        } finally {
            log.info("Order retrieval completed for id: {}", id);
        }
    }
}
