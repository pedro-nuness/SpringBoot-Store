package com.hype.application.dto.variation.variationsize;

import jakarta.validation.constraints.NotBlank;

public record VariationSizeRequestDTO (
        @NotBlank
        String size,

        int stock
){
}
