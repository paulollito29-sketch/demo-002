package com.example.controller;

import com.example.dto.*;
import com.example.service.SaleDetailService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/sale-details")
public class SaleDetailRestController {

    private final SaleDetailService saleDetailService;

    public SaleDetailRestController(SaleDetailService saleDetailService) {
        this.saleDetailService = saleDetailService;
    }

    @GetMapping
    public ResponseEntity<List<SaleDetailFindAll>> findAll(@RequestParam(required = false) Long saleId) {
        return ResponseEntity.ok(saleDetailService.findAll(saleId));
    }

    @PostMapping
    public ResponseEntity<SaleDetailCreated> post(@Valid @RequestBody SaleDetailCreate dto) {
        var saleDetail = saleDetailService.create(dto);
        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saleDetail.saleDetailId())
                .toUri();
        return ResponseEntity.created(location).body(saleDetail);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleDetailFindOne> findOne(@PathVariable Long id) {
        return ResponseEntity.ok(saleDetailService.findOne(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleDetailUpdated> put(@PathVariable Long id,
                                                 @Valid @RequestBody SaleDetailUpdate dto) {
        return ResponseEntity.ok(saleDetailService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        saleDetailService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
