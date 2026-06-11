package com.pritzit.benedict.northwind.backend.services;

import com.pritzit.benedict.northwind.backend.dtos.OrderDetailDto;
import com.pritzit.benedict.northwind.backend.dtos.OrderDto;
import com.pritzit.benedict.northwind.backend.entities.Order;
import com.pritzit.benedict.northwind.backend.entities.OrderDetail;
import com.pritzit.benedict.northwind.backend.exceptions.ResourceNotFoundException;
import com.pritzit.benedict.northwind.backend.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<OrderDto> getAllOrders() {
        log.info("Fetching all orders from DB");
        return orderRepository.findAll().stream()
                .map(this::toDtoWithoutDetails)
                .toList();
    }

    public OrderDto getOrderById(Integer id) {
        log.info("Fetching order with id {}", id);
        Optional<Order> orderOptional = orderRepository.findById(id);

        if (orderOptional.isPresent()) {
            OrderDto dto = toDto(orderOptional.get());
            log.info("Returning order DTO for id {}", id);
            return dto;
        }

        throw new ResourceNotFoundException("Order with id " + id + " not found");
    }

    private OrderDto toDtoWithoutDetails(Order entity) {
        return new OrderDto(
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getShipName(),
                entity.getShipCity(),
                entity.getShipCountry(),
                List.of()
        );
    }

    private OrderDto toDto(Order entity) {
        List<OrderDetailDto> details = entity.getOrderDetails().stream()
                .map(this::toDetailDto)
                .toList();

        return new OrderDto(
                entity.getOrderId(),
                entity.getCustomerId(),
                entity.getOrderDate(),
                entity.getShipName(),
                entity.getShipCity(),
                entity.getShipCountry(),
                details
        );
    }

    private OrderDetailDto toDetailDto(OrderDetail detail) {
        return new OrderDetailDto(
                detail.getId().getProductId(),
                detail.getUnitPrice(),
                detail.getQuantity(),
                detail.getDiscount()
        );
    }
}
