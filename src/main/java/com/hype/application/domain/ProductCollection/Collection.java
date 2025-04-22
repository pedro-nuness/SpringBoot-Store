package com.hype.application.domain.ProductCollection;

import com.hype.application.domain.ProductCollection.CollectionImage.CollectionImage;
import com.hype.application.dto.collection.CollectionRequestDTO;
import com.hype.application.domain.Product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "product_collection")
@Entity(name = "product_collection")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Collection {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    @OneToMany(mappedBy = "collection", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CollectionImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product_collection")
    private List<Product> products = new ArrayList<>();

    public Collection(CollectionRequestDTO collectionRequestDTO) {
        this.name = collectionRequestDTO.name();
    }
}