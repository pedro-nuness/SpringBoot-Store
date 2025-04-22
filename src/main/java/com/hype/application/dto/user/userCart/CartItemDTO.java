package com.hype.application.dto.user.userCart;


import com.hype.application.dto.image.ImageResponseDTO;

import java.util.List;

public record CartItemDTO(
        String productId,
        String productName,
        Double price,
        List<ImageResponseDTO> productImages,
        int quantity
) {}