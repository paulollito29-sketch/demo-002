package com.example.dto;

public record SaleFindOne(
        Long saleId,
        Double subTotal,
        Double tax,
        Double total,
        String description,
        Long customerId,
        String customerName
) {
}