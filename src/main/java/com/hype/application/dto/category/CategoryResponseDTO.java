package com.hype.application.dto.category;

import com.hype.application.dto.image.ImageResponseDTO;
import com.hype.application.domain.ProductCategory.Category;

import java.util.List;

public record CategoryResponseDTO(
        String id,
        String name,
        List<ImageResponseDTO> images
        //List<ProductResponseDTO> products
)
{
    public CategoryResponseDTO( Category category ){
        this(
                category.getId(),
                category.getName(),
                category.getImages().stream().map(ImageResponseDTO::new).toList()
                //category.getProducts().stream().map(ProductResponseDTO::new).toList()
        );

    }

}
