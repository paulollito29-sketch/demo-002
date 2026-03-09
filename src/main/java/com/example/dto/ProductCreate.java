package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreate(@NotNull(message = "name cannot be null")
                             @NotBlank(message = "name cannot be blank")
                             String name,
                            @NotNull(message = "price cannot be null")
                             @Min(value = 0, message = "price must be positive")
                             Double price,
                            @NotNull(message = "categoryId cannot be null")
                             Long categoryId) {
}
