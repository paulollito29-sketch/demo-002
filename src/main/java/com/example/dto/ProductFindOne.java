package com.example.dto;

import com.example.entity.CategoryEntity;

public record ProductFindOne(Long id,
                             String name,
                             Double price,
                             String categoryName) {
}
