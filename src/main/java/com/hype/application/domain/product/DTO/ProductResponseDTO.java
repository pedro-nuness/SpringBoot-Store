package com.hype.application.domain.product.DTO;


import com.hype.application.domain.product.Product;
import com.hype.application.domain.product.productimage.ProductImageResponseDTO;
import java.util.List;

public record ProductResponseDTO
        (String id,
         String name,
         Double price,
         String description,
         List<ProductImageResponseDTO> images
        ) {

    public ProductResponseDTO(Product product){
        this(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getImages().stream().map(ProductImageResponseDTO::new).toList()
        );
    }
}