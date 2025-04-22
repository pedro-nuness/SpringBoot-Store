package com.hype.application.controller.user;
import com.hype.application.dto.user.userCart.AddItemToCartDTO;
import com.hype.application.dto.user.userCart.CartResponseDTO;
import com.hype.application.service.user.userCart.UserCartService;

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

    @DeleteMapping("/{userId}")
    public ResponseEntity<CartResponseDTO> DeleteCartItem(@PathVariable String userId) {
        return ResponseEntity.ok(userCartService.getCartByUserId(userId));
    }




}
