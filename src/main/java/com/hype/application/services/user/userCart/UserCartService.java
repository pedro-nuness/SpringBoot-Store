package com.hype.application.services.user.userCart;


import com.hype.application.domain.product.Product;
import com.hype.application.domain.product.productimage.ProductImageResponseDTO;
import com.hype.application.domain.user.User;
import com.hype.application.domain.user.userCart.DTO.AddItemToCartDTO;
import com.hype.application.domain.user.userCart.DTO.CartItemDTO;
import com.hype.application.domain.user.userCart.DTO.CartResponseDTO;
import com.hype.application.domain.user.userCart.UserCart;
import com.hype.application.domain.user.userCart.UserCartItem;
import com.hype.application.exceptions.EventNotFoundException;
import com.hype.application.respositories.user.userCart.userCartItemRepository;
import com.hype.application.respositories.user.userCart.userCartRepository;
import com.hype.application.services.product.ProductServices;
import com.hype.application.services.user.UserServices;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCartService {

    @Autowired
    private UserServices userService;

    @Autowired
    private ProductServices productService;

    @Autowired
    private userCartRepository cartRepository;

    @Autowired
    private final userCartItemRepository cartItemRepository;

    @Transactional
    public void addItemToCart(AddItemToCartDTO dto) {
        User user = userService.AuthenticateUser(dto.userId(), false);

        Product product = productService.getProductRepository().findById(dto.productId())
                .orElseThrow(EventNotFoundException::new);

        UserCart cart = user.getCart();
        if (cart == null) {
            cart = new UserCart();
            cart.setUser(user);
            cartRepository.save(cart);
            user.setCart(cart);
        }

        Optional<UserCartItem> existingItemOpt = cart.getItems().stream()
                .filter(i -> i.getProduct().getId().equals(product.getId()))
                .findFirst();

        if (existingItemOpt.isPresent()) {
            UserCartItem existingItem = existingItemOpt.get();
            existingItem.setQuantity(existingItem.getQuantity() + dto.quantity());
        } else {
            UserCartItem newItem = new UserCartItem();
            newItem.setCart(cart);
            newItem.setProduct(product);
            newItem.setQuantity(dto.quantity());
            cart.getItems().add(newItem);
        }
        cartRepository.save(cart);
    }

    @Transactional
    public CartResponseDTO getCartByUserId(String userId) {
        User user = userService.AuthenticateUser(userId, false);

        UserCart cart = user.getCart();
        if (cart == null) {
            cart = new UserCart();
            cart.setUser(user);
            cartRepository.save(cart);
            user.setCart(cart);
        }

        List<CartItemDTO> items = cart.getItems().stream().map(item ->
                new CartItemDTO(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getProduct().getPrice(),
                        item.getProduct().getImages().stream().map(ProductImageResponseDTO::new).toList(),
                        item.getQuantity()
                )).toList();

        return new CartResponseDTO(cart.getId(), user.getId(), items);
    }


}

