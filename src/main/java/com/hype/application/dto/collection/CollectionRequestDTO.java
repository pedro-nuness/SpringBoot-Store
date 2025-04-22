package com.hype.application.dto.collection;

import jakarta.validation.constraints.NotBlank;

public record CollectionRequestDTO (
        @NotBlank
        String name
){
}
