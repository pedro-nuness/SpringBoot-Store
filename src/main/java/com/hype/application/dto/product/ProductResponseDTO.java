package com.hype.application.dto.product;


import com.hype.application.dto.collection.CollectionResponseDTO;
import com.hype.application.dto.image.ImageResponseDTO;
import com.hype.application.dto.type.ProductTypeResponseDTO;
import com.hype.application.dto.category.CategoryResponseDTO;
import com.hype.application.domain.Product.Product;
import com.hype.application.dto.variation.ProductVariationDTO;

import java.util.List;

public record ProductResponseDTO
        (String id,
         String name,
         Double price,
         String description,
         Double rating,
         Double promotion,
         List<ImageResponseDTO> images,
         CategoryResponseDTO category,
         ProductTypeResponseDTO productType,
         CollectionResponseDTO collection,
         List<ProductVariationDTO> variations
        ) {

    public ProductResponseDTO(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getRating(),
                product.getPromotionValue(),
                product.getImages().stream().map(ImageResponseDTO::new).toList(),
                new CategoryResponseDTO(product.getCategory()),
                new ProductTypeResponseDTO(product.getProduct_type()),
                new CollectionResponseDTO(product.getProduct_collection()),
                product.getVariations().stream().map(ProductVariationDTO::new).toList()
        );
    }
}