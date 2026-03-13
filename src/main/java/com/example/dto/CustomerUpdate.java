package com.example.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CustomerUpdate(
        @NotNull(message = "this cannot be null")
        @NotBlank(message = "this cannot be blank")
        String name){
}
