package com.pritzit.benedict.northwind.backend.dtos;

import java.time.LocalDate;
import java.util.List;

public record OrderDto(
        Integer orderId,
        String customerId,
        LocalDate orderDate,
        String shipName,
        String shipCity,
        String shipCountry,
        List<OrderDetailDto> orderDetails
) {
}
