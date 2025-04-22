package com.hype.application.domain.Product;

import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.domain.ProductType.ProductType;
import com.hype.application.domain.ProductCategory.Category;
import com.hype.application.dto.product.ProductRequestDTO;
import com.hype.application.domain.ProductImage.ProductImage;
import com.hype.application.domain.ProductVariation.ProductVariation;
import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "product")
@Entity(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;
    private Double price;
    private String description;
    private Double rating;
    private Double promotionValue;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @ManyToOne
    @JoinColumn(name = "product_collection_id")
    private Collection product_collection;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "product_type_id")
    private ProductType product_type;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariation> variations = new ArrayList<>();

    public Product(ProductRequestDTO dto){
        this.name = dto.name();
        this.price = dto.price();
        this.description = dto.description();
        this.rating = dto.rating();
        this.promotionValue = dto.promotionValue();
    }
}