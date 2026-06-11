package com.pritzit.benedict.northwind.backend.dtos;

import java.math.BigDecimal;

public record OrderDetailDto(
        Integer productId,
        BigDecimal unitPrice,
        Short quantity,
        Float discount
) {
}
