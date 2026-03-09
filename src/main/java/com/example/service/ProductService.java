package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.ProductMapper;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    public List<ProductFindAll> findAll() {
        return productRepository.findAllByEnabledIsTrueOrderByIdProductDesc().stream()
                .map(ProductMapper::toFindAll)
                .toList();
    }

    public ProductCreated create(ProductCreate dto) {
        if (productRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("product already exists");
        }
        var category = categoryRepository.findFirstByEnabledIsTrueAndIdCategory(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return ProductMapper.toCreated(productRepository.save(ProductMapper.toEntityCreated(dto, category)));
    }

    public ProductFindOne findOne(Long id) {
        var product = productRepository.findFirstByEnabledIsTrueAndIdProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("product does'nt exist"));
        return ProductMapper.toFindOne(product);
    }

    public ProductUpdated update(Long id, ProductUpdate dto) {
        var product = productRepository.findFirstByEnabledIsTrueAndIdProduct(id)
                .orElseThrow(() -> new ResourceNotFoundException("product does'nt exist"));
        var category = categoryRepository.findFirstByEnabledIsTrueAndIdCategory(dto.categoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if (productRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("product already exists");
        }
        return ProductMapper.toUpdated(productRepository.save(ProductMapper.toEntityUpdated(product, dto, category)));
    }

    public void delete(Long id) {
        productRepository.findFirstByEnabledIsTrueAndIdProduct(id)
                .map(item -> productRepository.save(ProductMapper.toEntityDeleted(item)))
                .orElseThrow(() -> new ResourceNotFoundException("product does'nt exist"));

    }

}
