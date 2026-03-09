package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CategoryEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class CategoryMapper {

    private CategoryMapper() {
    }
    public static List<ProductFindAll> toProducts(CategoryEntity entity){
        if (entity.getProduct() == null){
            return Collections.emptyList();
        }
        return entity.getProduct().stream()
                .map(ProductMapper::toFindAll)
                .toList();
    }

    public static CategoryFindAll ToCategoryFindAll(CategoryEntity entity) {
        return new CategoryFindAll(entity.getIdCategory(), entity.getName(), toProducts(entity));
    }

    public static CategoryEntity ToEntity(CategoryCreate dto){
        return CategoryEntity.builder()
                .name(dto.name())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CategoryCreated ToCategoryCreated(CategoryEntity entity){
        return new CategoryCreated(entity.getIdCategory(), entity.getName());
    }

    public static CategoryFindOne ToCategoryFindOne(CategoryEntity entity){
        return new CategoryFindOne(entity.getIdCategory(), entity.getName(), toProducts(entity));
    }

    public static CategoryEntity ToEntity(CategoryEntity entity, CategoryUpdate dto){
        entity.setName(dto.name());
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static CategoryUpdated ToCategoryUpdated(CategoryEntity entity){
        return new CategoryUpdated(entity.getIdCategory(), entity.getName());
    }

    public static CategoryEntity ToEntity(CategoryEntity entity){
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }
}
