package com.example.dto;

import java.time.LocalDate;

public record SaleCreated(
        Long saleId,
        Double subTotal,
        Double tax,
        Double total,
        String description,
        Long customerId,
        LocalDate saleDate
) {
}