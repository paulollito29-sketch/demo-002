package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SaleMapper;
import com.example.repository.CustomerRepository;
import com.example.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;

    public SaleService(SaleRepository saleRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
    }

    public List<SaleFindAll> findAll() {
        return saleRepository.findAllByEnabledIsTrueOrderByIdSaleDesc().stream()
                .map(SaleMapper::toFindAll)
                .toList();
    }

    public SaleCreated create(SaleCreate dto) {
        var customer = customerRepository.findFirstByEnabledIsTrueAndIdCustomer(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        var entity = SaleMapper.toEntityCreated(dto, customer);
        return SaleMapper.toCreated(saleRepository.save(entity));
    }

    public SaleFindOne findOne(Long id) {
        var sale = saleRepository.findFirstByEnabledIsTrueAndIdSale(id)
                .orElseThrow(() -> new ResourceNotFoundException("sale doesn't exist"));
        return SaleMapper.toFindOne(sale);
    }

    public SaleUpdated update(Long id, SaleUpdate dto) {
        var sale = saleRepository.findFirstByEnabledIsTrueAndIdSale(id)
                .orElseThrow(() -> new ResourceNotFoundException("sale doesn't exist"));
        var customer = customerRepository.findFirstByEnabledIsTrueAndIdCustomer(dto.customerId())
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        var updated = SaleMapper.toEntityUpdated(sale, dto, customer);
        return SaleMapper.toUpdated(saleRepository.save(updated));
    }

    public void delete(Long id) {
        saleRepository.findFirstByEnabledIsTrueAndIdSale(id)
                .map(SaleMapper::toEntityDeleted)
                .map(saleRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("sale doesn't exist"));
    }
}
