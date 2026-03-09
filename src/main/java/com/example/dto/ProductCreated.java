package com.example.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductCreated(Long productId,
                             String name,
                             Double price,
                             Long categoryId) {
}
