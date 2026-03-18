package com.example.mapper;


import com.example.dto.SaleConsultBetweenDate;
import com.example.dto.SalesFilteredDto;
import com.example.entity.SaleEntity;

import java.util.List;

public class ConsultMapper {

    private ConsultMapper() {
    }
    //implementar el metodo para convertir a saleConsultBetweenDate
    public static SaleConsultBetweenDate toDto(SaleEntity sale) {
        return new SaleConsultBetweenDate(
                sale.getCustomer().getName(),
                sale.getSubTotal(),
                sale.getTax(),
                sale.getTotal(),
                sale.getSaleDate()
        );
    }

    public static SalesFilteredDto toSaleConsultBetweenDate(List<SaleEntity> sales) {
        return new SalesFilteredDto(
                sales.stream().map(ConsultMapper::toDto).toList()
        );
    }
}
