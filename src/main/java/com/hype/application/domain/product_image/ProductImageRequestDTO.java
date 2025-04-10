package com.hype.application.domain.product_image;

import com.hype.application.domain.product.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductImageRequestDTO(
        @NotBlank
        String URL
) {
}
