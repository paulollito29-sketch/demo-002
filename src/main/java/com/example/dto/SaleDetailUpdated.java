package com.example.dto;

public record SaleDetailUpdated(
        Long saleDetailId,
        Integer quantity,
        Double unitPrice,
        Double total,
        Long productId,
        Long saleId
) {
}
