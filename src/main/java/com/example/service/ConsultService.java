package com.example.service;

import com.example.dto.SaleBetweenDatesDto;
import com.example.entity.SaleEntity;
import com.example.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultService {

    private final SaleRepository saleRepository;

    public ConsultService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<SaleEntity> getSaleFromTo(SaleBetweenDatesDto saleBetweenDatesDTO) {
        return saleRepository.findSalesByDateRange(saleBetweenDatesDTO.startDate(), saleBetweenDatesDTO.endDate());
    }
}
