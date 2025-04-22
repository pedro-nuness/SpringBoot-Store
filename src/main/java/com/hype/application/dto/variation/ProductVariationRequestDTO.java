package com.hype.application.dto.variation;

import com.hype.application.dto.variation.variationsize.VariationSizeRequestDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;


public record ProductVariationRequestDTO (
        @NotBlank
        String color,

        List<VariationSizeRequestDTO> sizes)
{
}
