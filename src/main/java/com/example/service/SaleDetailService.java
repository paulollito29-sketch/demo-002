package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.SaleDetailMapper;
import com.example.repository.ProductRepository;
import com.example.repository.SaleDetailRepository;
import com.example.repository.SaleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleDetailService {

    private final SaleDetailRepository saleDetailRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleDetailService(SaleDetailRepository saleDetailRepository, SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleDetailRepository = saleDetailRepository;
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public List<SaleDetailFindAll> findAll(Long saleId) {
        var items = saleId == null
                ? saleDetailRepository.findAllByEnabledIsTrueOrderByIdSaleDetailDesc()
                : saleDetailRepository.findAllBySale_IdSaleAndEnabledIsTrueOrderByIdSaleDetailDesc(saleId);
        return items.stream()
                .map(SaleDetailMapper::toFindAll)
                .toList();
    }

    public SaleDetailCreated create(SaleDetailCreate dto) {
        var sale = saleRepository.findFirstByEnabledIsTrueAndIdSale(dto.saleId())
                .orElseThrow(() -> new ResourceNotFoundException("sale doesn't exist"));
        var product = productRepository.findFirstByEnabledIsTrueAndIdProduct(dto.productId())
                .orElseThrow(() -> new ResourceNotFoundException("product not found"));
        if (saleDetailRepository.existsByEnabledIsTrueAndSale_IdSaleAndProduct_IdProduct(dto.saleId(), dto.productId())) {
            throw new ResourceAlreadyExistsException("product already added to this sale");
        }
        var entity = SaleDetailMapper.toEntityCreated(dto, product, sale);
        return SaleDetailMapper.toCreated(saleDetailRepository.save(entity));
    }

    public SaleDetailFindOne findOne(Long id) {
        var saleDetail = saleDetailRepository.findFirstByEnabledIsTrueAndIdSaleDetail(id)
                .orElseThrow(() -> new ResourceNotFoundException("sale detail doesn't exist"));
        return SaleDetailMapper.toFindOne(saleDetail);
    }

    public SaleDetailUpdated update(Long id, SaleDetailUpdate dto) {
        var saleDetail = saleDetailRepository.findFirstByEnabledIsTrueAndIdSaleDetail(id)
                .orElseThrow(() -> new ResourceNotFoundException("sale detail doesn't exist"));
        var sale = saleRepository.findFirstByEnabledIsTrueAndIdSale(dto.saleId())
                .orElseThrow(() -> new ResourceNotFoundException("sale doesn't exist"));
        var product = productRepository.findFirstByEnabledIsTrueAndIdProduct(dto.productId())
                .orElseThrow(() -> new ResourceNotFoundException("product not found"));
        if (saleDetailRepository.existsByEnabledIsTrueAndSale_IdSaleAndProduct_IdProductAndIdSaleDetailNot(dto.saleId(), dto.productId(), id)) {
            throw new ResourceAlreadyExistsException("product already added to this sale");
        }
        var updated = SaleDetailMapper.toEntityUpdated(saleDetail, dto, product, sale);
        return SaleDetailMapper.toUpdated(saleDetailRepository.save(updated));
    }

    public void delete(Long id) {
        saleDetailRepository.findFirstByEnabledIsTrueAndIdSaleDetail(id)
                .map(SaleDetailMapper::toEntityDeleted)
                .map(saleDetailRepository::save)
                .orElseThrow(() -> new ResourceNotFoundException("sale detail doesn't exist"));
    }
}
