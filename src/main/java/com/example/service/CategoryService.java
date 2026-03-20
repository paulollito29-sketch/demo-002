package com.example.service;

import com.example.dto.*;
import com.example.exception.ResourceAlreadyExistsException;
import com.example.exception.ResourceNotFoundException;
import com.example.mapper.CategoryMapper;
import com.example.repository.CategoryRepository;
import com.example.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public CategoryService(CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    public List<CategoryFindAll> findAll() {
        return categoryRepository.findAllByEnabledIsTrueOrderByIdCategoryDesc().stream()
                //.map(item -> CategoryMapper.ToCategoryFindAll(item))
                .map(CategoryMapper::ToCategoryFindAll)
                .toList();
    }

    public CategoryCreated  create(CategoryCreate dto) {
        if (categoryRepository.existsByEnabledIsTrueAndNameIgnoreCase(dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var categoryEntity = CategoryMapper.ToEntity(dto);
        var categorySaved = categoryRepository.save(categoryEntity);
        return CategoryMapper.ToCategoryCreated(categorySaved);
    }

    public CategoryFindOne findOne(Long id) {
        var category = categoryRepository.findByEnabledIsTrueAndIdCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        return CategoryMapper.ToCategoryFindOne(category);
    }

    public CategoryUpdated update(Long id, CategoryUpdate dto) {
        var category = categoryRepository.findByEnabledIsTrueAndIdCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        if (categoryRepository.
                existsByEnabledIsTrueAndIdCategoryNotAndNameIgnoreCase(id, dto.name())) {
            throw new ResourceAlreadyExistsException("this name: " + dto.name() + " already exists");
        }
        var categoryUpdate = CategoryMapper.ToEntity(category, dto);
        var categorySaved = categoryRepository.save(categoryUpdate);
        return CategoryMapper.ToCategoryUpdated(categorySaved);
    }

    public void delete(Long id) {
        var category = categoryRepository.findByEnabledIsTrueAndIdCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
        if(!productRepository.findAllByEnabledIsTrueAndCategory_IdCategory(id).isEmpty()) {
            throw new ResourceAlreadyExistsException("Category has products, can't be deleted.");
        }
        var categoryDeleted = CategoryMapper.ToEntity(category);
        categoryRepository.save(categoryDeleted);
    }

}
