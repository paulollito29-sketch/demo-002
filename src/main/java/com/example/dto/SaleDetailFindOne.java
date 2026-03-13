package com.example.dto;

public record SaleDetailFindOne(
        Long saleDetailId,
        Integer quantity,
        Double unitPrice,
        Double total,
        Long productId,
        String productName,
        Long saleId
) {
}
