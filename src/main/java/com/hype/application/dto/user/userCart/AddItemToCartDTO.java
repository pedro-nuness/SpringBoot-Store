package com.hype.application.dto.user.userCart;


public record AddItemToCartDTO(
        String userId,
        String productId,
        int quantity
) {}