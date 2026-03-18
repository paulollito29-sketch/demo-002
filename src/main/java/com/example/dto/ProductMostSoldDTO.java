package com.example.dto;

public record ProductMostSoldDTO(
        Long id,
        String name,
        String categoryName,
        Long totalSold
) {}
