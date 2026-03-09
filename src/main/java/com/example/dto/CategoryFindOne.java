package com.example.dto;

import java.util.List;

public record CategoryFindOne(Long categoryId, String categoryName, List<ProductFindAll> products) {
}
