package com.hype.application.domain.ProductVariation;

import com.hype.application.domain.Product.Product;
import com.hype.application.dto.variation.ProductVariationRequestDTO;
import com.hype.application.domain.ProductVariation.VariationSize.VariationSize;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "product_variation")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProductVariation {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String color;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "variation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VariationSize> sizes = new ArrayList<>();

    @OneToMany(mappedBy = "productVariation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariationImage> images = new ArrayList<>();

    public ProductVariation(ProductVariationRequestDTO dto){
        this.color = dto.color();
        this.sizes = dto.sizes().stream()
                .map(VariationSize::new)
                .collect(Collectors.toList());
    }
}