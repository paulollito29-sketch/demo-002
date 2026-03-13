package com.example.dto;

public record SaleCreated(
        Long saleId,
        Double subTotal,
        Double tax,
        Double total,
        String description,
        Long customerId
) {
}