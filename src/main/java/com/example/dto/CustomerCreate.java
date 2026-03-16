package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CustomerCreate(
        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be blank")
        String name,

        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be blank")
        @Size(min = 8, max = 8, message = "dni must be exactly 8 characters long")
                String dni){
}
