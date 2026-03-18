package com.example.dto;

import java.time.LocalDate;

public record SaleConsultBetweenDate (String name,
                                      Double subtotal,
                                      Double tax,
                                      Double total,
                                      LocalDate saleDate) {
}
