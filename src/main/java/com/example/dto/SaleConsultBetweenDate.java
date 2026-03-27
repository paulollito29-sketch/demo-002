package com.example.dto;

import java.time.LocalDate;

public record SaleConsultBetweenDate (Long saleId,
                                      String name,
                                      Double subtotal,
                                      Double tax,
                                      Double total,
                                      LocalDate saleDate) {
}
