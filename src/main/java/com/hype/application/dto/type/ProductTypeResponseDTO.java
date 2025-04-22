package com.hype.application.dto.type;

import com.hype.application.dto.image.ImageResponseDTO;
import com.hype.application.domain.ProductType.ProductType;

import java.util.List;

import java.util.Optional;
import java.util.Collections;

public record ProductTypeResponseDTO (
        String id,
        String name,
        List<ImageResponseDTO> images
       // List<ProductResponseDTO> products
) {
    public ProductTypeResponseDTO(ProductType productType) {
        this(
                productType.getId(),
                productType.getName(),
                Optional.ofNullable(productType.getImages())
                        .orElse(Collections.emptyList())
                        .stream().map(ImageResponseDTO::new).toList()
        );
    }
}
