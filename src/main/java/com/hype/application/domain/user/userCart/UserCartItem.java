package com.hype.application.domain.user.userCart;

import com.hype.application.domain.Product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_cart_item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UserCartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private UserCart cart;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
}
