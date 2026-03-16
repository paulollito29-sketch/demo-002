package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CustomerEntity;
import com.example.entity.SaleEntity;

import java.time.LocalDateTime;

public class SaleMapper {

    private SaleMapper() {
    }

    public static SaleEntity toEntityCreated(SaleCreate dto, CustomerEntity customer) {
        return SaleEntity.builder()
                .subTotal(0.0)
                .tax(0.0)
                .total(0.0)
                .description(dto.description())
                .customer(customer)
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static SaleEntity toEntityUpdated(SaleEntity entity, SaleUpdate dto, CustomerEntity customer) {
        entity.setSubTotal(0.0);
        entity.setTax(0.0);
        entity.setTotal(0.0);
        entity.setDescription(dto.description());
        entity.setCustomer(customer);
        entity.setUpdatedAt(LocalDateTime.now());
        return entity;
    }

    public static SaleEntity toEntityDeleted(SaleEntity entity) {
        entity.setEnabled(false);
        entity.setDeletedAt(LocalDateTime.now());
        return entity;
    }

    public static SaleCreated toCreated(SaleEntity entity) {
        return new SaleCreated(
                entity.getIdSale(),
                entity.getSubTotal(),
                entity.getTax(),
                entity.getTotal(),
                entity.getDescription(),
                entity.getCustomer().getIdCustomer()
        );
    }

    public static SaleUpdated toUpdated(SaleEntity entity) {
        return new SaleUpdated(
                entity.getIdSale(),
                entity.getSubTotal(),
                entity.getTax(),
                entity.getTotal(),
                entity.getDescription(),
                entity.getCustomer().getIdCustomer()
        );
    }

    public static SaleFindOne toFindOne(SaleEntity entity) {
        return new SaleFindOne(
                entity.getIdSale(),
                entity.getSubTotal(),
                entity.getTax(),
                entity.getTotal(),
                entity.getDescription(),
                entity.getCustomer().getIdCustomer(),
                entity.getCustomer().getName()
        );
    }

    public static SaleFindAll toFindAll(SaleEntity entity) {
        return new SaleFindAll(
                entity.getIdSale(),
                entity.getSubTotal(),
                entity.getTax(),
                entity.getTotal(),
                entity.getDescription(),
                entity.getCustomer().getIdCustomer(),
                entity.getCustomer().getName()
        );
    }
}