package com.hype.application.domain.user.userCart.DTO;

import java.util.List;

public record CartResponseDTO(
        String cartId,
        String userId,
        List<CartItemDTO> items
) {}