package com.hype.application.domain.ProductImage;

import com.hype.application.domain.Image.ImageEntity;
import com.hype.application.domain.Product.Product;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "product_image")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public ProductImage(String URL) {
        this.setUrl( URL );
    }
}
