package com.example.controller;

import com.example.dto.*;
import com.example.service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleRestController {

    private final SaleService saleService;

    public SaleRestController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleFindAll>> findAll() {
        return ResponseEntity.ok(saleService.findAll());
    }

    @PostMapping
    public ResponseEntity<SaleCreated> post(@Valid @RequestBody SaleCreate dto) {
        var sale = saleService.create(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(sale.saleId())
                .toUri();
        return ResponseEntity.created(location).body(sale);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleFindOne> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(saleService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleUpdated> put(@PathVariable Long id,
                                           @Valid @RequestBody SaleUpdate dto) {
        return ResponseEntity.ok(saleService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
