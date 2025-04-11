package com.hype.application.domain.product.productimage;

import jakarta.validation.constraints.NotBlank;

public record ProductImageRequestDTO(
        @NotBlank
        String URL
) {
}
