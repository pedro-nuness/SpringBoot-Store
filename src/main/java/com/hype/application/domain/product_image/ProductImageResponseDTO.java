package com.hype.application.domain.product_image;

public record ProductImageResponseDTO(
        String id,
        String url
) {
    public ProductImageResponseDTO(ProductImage image) {
        this(image.getId(), image.getUrl());
    }
}