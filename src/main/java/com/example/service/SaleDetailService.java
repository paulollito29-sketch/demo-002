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
        double lineTotal = product.getPrice() * dto.quantity();
        sale.setSubTotal(sale.getSubTotal() + lineTotal);
        sale.setTax(sale.getSubTotal() * 0.18);
        sale.setTotal(sale.getSubTotal() + sale.getTax());
        saleRepository.save(sale);
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
        // Subtract old line total
        double oldLineTotal = saleDetail.getUnitPrice() * saleDetail.getQuantity();
        sale.setSubTotal(sale.getSubTotal() - oldLineTotal);
        // Add new line total
        double newLineTotal = product.getPrice() * dto.quantity();
        sale.setSubTotal(sale.getSubTotal() + newLineTotal);
        // Recalculate tax and total
        sale.setTax(sale.getSubTotal() * 0.18);
        sale.setTotal(sale.getSubTotal() + sale.getTax());
        saleRepository.save(sale);
        var updated = SaleDetailMapper.toEntityUpdated(saleDetail, dto, product, sale);
        return SaleDetailMapper.toUpdated(saleDetailRepository.save(updated));
    }

    public void delete(Long id) {
        var saleDetail = saleDetailRepository.findFirstByEnabledIsTrueAndIdSaleDetail(id)
                .orElseThrow(() -> new ResourceNotFoundException("sale detail doesn't exist"));
        var sale = saleDetail.getSale();
        double lineTotal = saleDetail.getUnitPrice() * saleDetail.getQuantity();
        sale.setSubTotal(sale.getSubTotal() - lineTotal);
        sale.setTax(sale.getSubTotal() * 0.18);
        sale.setTotal(sale.getSubTotal() + sale.getTax());
        saleRepository.save(sale);
        saleDetailRepository.save(SaleDetailMapper.toEntityDeleted(saleDetail));
    }
}
