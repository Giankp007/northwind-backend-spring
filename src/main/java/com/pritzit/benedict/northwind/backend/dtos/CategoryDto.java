package com.pritzit.benedict.northwind.backend.dtos;

public record CategoryDto(
        Short categoryId,
        String categoryName,
        String description
) {
}
