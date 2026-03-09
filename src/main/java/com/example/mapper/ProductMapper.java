package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CategoryEntity;
import com.example.entity.ProductEntity;

import java.time.LocalDateTime;

public class ProductMapper {

    private ProductMapper() {
    }

    public static ProductEntity toEntityCreated(ProductCreate dto, CategoryEntity category) {
        return ProductEntity.builder()
                .name(dto.name())
                .price(dto.price())
                .category(category)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static ProductEntity toEntityUpdated(ProductEntity entity, ProductUpdate dto, CategoryEntity category) {
        entity.setName(dto.name());
        entity.setPrice(dto.price());
        entity.setCategory(category);
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static ProductEntity toEntityDeleted(ProductEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }

    public static ProductFindAll toFindAll(ProductEntity entity) {
        if(entity.getEnabled() == false){
            return null;
        }
        return new ProductFindAll(
                entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getName()
        );
    }

    public static ProductFindOne toFindOne(ProductEntity entity) {
        return new ProductFindOne(
                entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getName()
        );
    }

    public static ProductCreated toCreated(ProductEntity entity) {
        return new ProductCreated(
                entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getIdCategory()
        );
    }

    public static ProductUpdated toUpdated(ProductEntity entity) {
        return new ProductUpdated(
                entity.getIdProduct(),
                entity.getName(),
                entity.getPrice(),
                entity.getCategory().getIdCategory()
        );
    }


}
