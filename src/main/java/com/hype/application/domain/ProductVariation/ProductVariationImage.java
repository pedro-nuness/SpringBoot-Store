package com.hype.application.domain.ProductVariation;


import com.hype.application.domain.Image.ImageEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Table(name = "product_variation_image")
@Entity(name = "product_variation_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductVariationImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name = "product_variation_id")
    private ProductVariation productVariation;

    public ProductVariationImage(String fileUrl, ProductVariation productVariation) {
        super(fileUrl);
        this.productVariation = productVariation;
    }
}