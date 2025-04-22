package com.hype.application.dto.user.userCart;

import java.util.List;

public record CartResponseDTO(
        String cartId,
        String userId,
        List<CartItemDTO> items
) {}