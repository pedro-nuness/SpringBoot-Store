package com.hype.application.domain.ProductType.ProductTypeImage;

import com.hype.application.domain.Image.ImageEntity;
import com.hype.application.domain.ProductType.ProductType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Table(name = "product_type_image")
@Entity(name = "product_type_image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductTypeImage extends ImageEntity {

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType product_type;

    public ProductTypeImage(String fileUrl, ProductType productType) {
        super(fileUrl);
        this.product_type = productType;
    }
}