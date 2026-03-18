package com.example.service;

import com.example.dto.SaleBetweenDatesDto;
import com.example.dto.SalesFilteredDto;
import com.example.entity.SaleEntity;
import com.example.mapper.ConsultMapper;
import com.example.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {

    private final SaleRepository saleRepository;

    public ConsultService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public SalesFilteredDto getSaleFromTo(SaleBetweenDatesDto saleBetweenDatesDTO) {
        var salesFiltered = saleRepository.findSalesByDateRange(saleBetweenDatesDTO.startDate(), saleBetweenDatesDTO.endDate()).stream().toList();
        return ConsultMapper.toSaleConsultBetweenDate(salesFiltered);
    }
}
