package com.hype.application.controller.user;
import com.hype.application.domain.user.userCart.DTO.AddItemToCartDTO;
import com.hype.application.domain.user.userCart.DTO.CartResponseDTO;
import com.hype.application.services.user.userCart.UserCartService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/cart")
public class UserCartController {

    @Autowired
    UserCartService userCartService;

    @PostMapping
    public ResponseEntity<String> AddCartItem(@RequestBody AddItemToCartDTO dto) {
        userCartService.addItemToCart(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable String userId) {
        return ResponseEntity.ok(userCartService.getCartByUserId(userId));
    }


}
