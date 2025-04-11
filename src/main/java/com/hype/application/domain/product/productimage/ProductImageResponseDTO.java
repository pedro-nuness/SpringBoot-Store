package com.hype.application.domain.product.productimage;

public record ProductImageResponseDTO(
        String id,
        String url
) {
    public ProductImageResponseDTO(ProductImage image) {
        this(image.getId(), image.getUrl());
    }
}