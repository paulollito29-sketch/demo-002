package com.example.controller;

import com.example.dto.SaleBetweenDatesDto;
import com.example.dto.SalesFilteredDto;
import com.example.service.ConsultService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/consult")
@CrossOrigin(origins = "*")
public class ConsultRestController {

    private final ConsultService consultService;

    public ConsultRestController(ConsultService consultService) {
        this.consultService = consultService;
    }

    @GetMapping("/sales-between-dates")
    public SalesFilteredDto getSalesBetweenDates(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        return consultService.getSaleFromTo(
                new SaleBetweenDatesDto(startDate, endDate));
    }
}