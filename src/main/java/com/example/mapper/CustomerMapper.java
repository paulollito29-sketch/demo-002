package com.example.mapper;

import com.example.dto.*;
import com.example.entity.CategoryEntity;
import com.example.entity.CustomerEntity;

import java.time.LocalDateTime;

public class CustomerMapper {

    private CustomerMapper(){}

    public static CustomerFindAll toCustomerFindAll(CustomerEntity entity){
        return new CustomerFindAll(entity.getIdCustomer(), entity.getName(), entity.getDni());
    }

    public static CustomerEntity toEntityCreated(CustomerCreate dto){
        return CustomerEntity.builder()
                .name(dto.name())
                .dni(dto.dni())
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static CustomerCreated toCustomerCreated(CustomerEntity entity){
        return new CustomerCreated(entity.getIdCustomer(), entity.getName(), entity.getDni());
    }

    public static CustomerFindOne toCustomerFindOne(CustomerEntity entity){
        return new CustomerFindOne(entity.getIdCustomer(), entity.getName(), entity.getDni());
    }

    public static CustomerEntity toEntityUpdated(CustomerUpdate dto, CustomerEntity entity){
        entity.setUpdatedAt(LocalDateTime.now());
        entity.setName(dto.name());
        entity.setDni(dto.dni());
        return entity;
    }

    public static CustomerUpdated toCustomerUpdated(CustomerEntity entity){
        return new CustomerUpdated(entity.getIdCustomer(), entity.getName(), entity.getDni());
    }

    public static CustomerEntity toCustomerDeleted(CustomerEntity entity){
        entity.setDeletedAt(LocalDateTime.now());
        entity.setEnabled(false);
        return entity;
    }

}
