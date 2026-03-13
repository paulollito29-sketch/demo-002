package com.example.dto;

public record SaleUpdated(
        Long saleId,
        Double subTotal,
        Double tax,
        Double total,
        String description,
        Long customerId
) {
}