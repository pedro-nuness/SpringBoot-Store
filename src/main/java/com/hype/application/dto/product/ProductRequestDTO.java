package com.hype.application.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record ProductRequestDTO(
        @NotBlank
        String name,

        @NotNull
        Double price,

        @NotBlank
        String description,

        @NotNull
        Double rating,

        @NotNull
        Double promotionValue,

        @NotBlank
        String category,

        @NotBlank
        String product_type
) {
}