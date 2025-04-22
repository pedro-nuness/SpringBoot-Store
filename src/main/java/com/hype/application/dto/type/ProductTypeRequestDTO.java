package com.hype.application.dto.type;

import jakarta.validation.constraints.NotBlank;

public record ProductTypeRequestDTO(
        @NotBlank
        String name
) {
}
