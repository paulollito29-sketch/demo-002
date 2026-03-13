package com.example.dto;

public record SaleDetailCreated(
        Long saleDetailId,
        Integer quantity,
        Double unitPrice,
        Double total,
        Long productId,
        Long saleId
) {
}
