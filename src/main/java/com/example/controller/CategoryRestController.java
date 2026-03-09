package com.example.controller;

import com.example.dto.*;
import com.example.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {

    private final CategoryService categoryService;

    public CategoryRestController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryFindAll>> get() {
        var Categories = categoryService.findAll();
        return ResponseEntity.ok(Categories);
    }

    @PostMapping
    public ResponseEntity<CategoryCreated> post(@Valid @RequestBody CategoryCreate dto) {
        var categoryCreated = categoryService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(categoryCreated.idCategory())
                .toUri();
        return ResponseEntity.created(location).body(categoryCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryUpdated> update(@PathVariable Long id,
                                                  @RequestBody CategoryUpdate dto) {
        var categoryUpdated = categoryService.update(id, dto);
        return ResponseEntity.ok(categoryUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryFindOne> findOne(@PathVariable Long id) {
        var category = categoryService.findOne(id);
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
