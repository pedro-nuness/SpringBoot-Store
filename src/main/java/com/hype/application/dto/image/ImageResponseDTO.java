package com.hype.application.dto.image;

import com.hype.application.domain.Image.ImageEntity;

public record ImageResponseDTO(String id,
                               String url
)
{
    public ImageResponseDTO(ImageEntity image){
        this(
                image.getId(),
                image.getUrl()
        );
    }
}
