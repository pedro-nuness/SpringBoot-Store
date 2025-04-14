package com.hype.application.domain.user.userCart.DTO;


public record AddItemToCartDTO(
        String userId,
        String productId,
        int quantity
) {}