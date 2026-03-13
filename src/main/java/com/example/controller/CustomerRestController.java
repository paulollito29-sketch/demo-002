package com.example.controller;

import com.example.dto.*;
import com.example.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerFindAll>> get() {
        var Customers = customerService.findAll();
        return ResponseEntity.ok(Customers);
    }

    @PostMapping
    public ResponseEntity<CustomerCreated> post(@Valid @RequestBody CustomerCreate dto) {
        var CustomerCreated = customerService.create(dto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(CustomerCreated.customerId())
                .toUri();
        return ResponseEntity.created(location).body(CustomerCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerUpdated> update(@PathVariable Long id,
                                                  @RequestBody CustomerUpdate dto) {
        var CustomerUpdated = customerService.update(dto, id);
        return ResponseEntity.ok(CustomerUpdated);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerFindOne> findOne(@PathVariable Long id) {
        var Customer = customerService.findOne(id);
        return ResponseEntity.ok(Customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
