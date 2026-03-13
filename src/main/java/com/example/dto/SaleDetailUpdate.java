package com.example.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record SaleDetailUpdate(
        @NotNull(message = "quantity cannot be null")
        @Positive(message = "quantity must be greater than 0")
        Integer quantity,

        @NotNull(message = "productId cannot be null")
        Long productId,

        @NotNull(message = "saleId cannot be null")
        Long saleId
) {
}
