package com.hype.application.dto.collection;

import com.hype.application.domain.ProductCollection.Collection;
import com.hype.application.dto.image.ImageResponseDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.Collections;
import java.util.List;

public record CollectionResponseDTO(
        @NotBlank
        String id,

        @NotBlank
        String name,

        List<ImageResponseDTO> images
        )
{
    public CollectionResponseDTO(Collection collection) {
        this(
                collection != null ? collection.getId() : "null",
                collection != null ? collection.getName() : "null",
                collection != null ?
                        collection.getImages().stream().map(ImageResponseDTO::new).toList() :
                        Collections.emptyList()
        );
    }

}
