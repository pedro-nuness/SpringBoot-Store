package com.hype.application.domain.user.userCart.DTO;

import com.hype.application.domain.product.productimage.ProductImageResponseDTO;

import java.util.List;

public record CartItemDTO(
        String productId,
        String productName,
        Double price,
        List<ProductImageResponseDTO> productImages,
        int quantity
) {}