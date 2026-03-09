package com.example.dto;

import java.util.List;

public record CategoryFindAll(Long categoryId, String name, List<ProductFindAll> products) {
}
