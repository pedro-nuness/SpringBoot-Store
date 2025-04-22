package com.hype.application.dto.variation.variationsize;

import com.hype.application.domain.ProductVariation.VariationSize.VariationSize;
import jakarta.validation.constraints.NotBlank;

public record VariationSizeDTO(

        @NotBlank
        String size, Integer stock) {

        public VariationSizeDTO(VariationSize variationSize) {
                this(
                        variationSize.getSize(),
                        variationSize.getStock()
                );
        }

}
