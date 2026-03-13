package com.example.dto;

public record SaleFindAll(
        Long saleId,
        Double subTotal,
        Double tax,
        Double total,
        String description,
        Long customerId,
        String customerName
) {
}