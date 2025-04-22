package com.hype.application.domain.ProductType;

import com.hype.application.dto.type.ProductTypeRequestDTO;
import com.hype.application.domain.ProductType.ProductTypeImage.ProductTypeImage;
import com.hype.application.domain.Product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Table(name = "product_type")
@Entity(name = "product_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductType {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "product_type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductTypeImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product_type", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    public ProductType(ProductTypeRequestDTO productTypeRequestDTO){
        this.name = productTypeRequestDTO.name();
    }
}