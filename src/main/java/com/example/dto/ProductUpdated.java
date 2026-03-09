package com.example.dto;

public record ProductUpdated(Long productId,
                             String name,
                             Double price,
                             Long categoryId) {
}
