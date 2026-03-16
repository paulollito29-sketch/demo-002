package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record SaleUpdate(
        @NotNull(message = "this cannot be null")
        @PositiveOrZero(message = "must be greater than or equal to 0")
        Double subTotal,

        @NotNull(message = "this cannot be null")
        @PositiveOrZero(message = "must be greater than or equal to 0")
        Double total,

        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be blank")
        String description,

        @NotNull(message = "this cannot be null")
        Long customerId
) {
}