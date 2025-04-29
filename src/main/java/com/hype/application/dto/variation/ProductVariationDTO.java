package com.hype.application.dto.variation;

import com.hype.application.domain.Image.ImageEntity;
import com.hype.application.domain.ProductVariation.ProductVariation;
import com.hype.application.dto.image.ImageResponseDTO;
import com.hype.application.dto.variation.variationsize.VariationSizeDTO;

import java.util.List;

public record ProductVariationDTO(
        String id,
        String color,
        List<VariationSizeDTO> sizes,
        List<ImageResponseDTO> images)

{
        public ProductVariationDTO(ProductVariation productVariation) {
            this(
                    productVariation.getId(),
                    productVariation.getColor(),
                    productVariation.getSizes().stream().map(VariationSizeDTO::new).toList(),
                    productVariation.getImages().stream().map(ImageResponseDTO::new).toList()
            );
        }
}

