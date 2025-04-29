package com.hype.application.dto.auth.request;

import jakarta.validation.constraints.*;

public record RegisterDTO (
        @NotEmpty @Email String email,  // Valida o formato do email
        @NotEmpty String password,
        @NotEmpty String firstname,
        @NotEmpty String lastname,
        @NotNull @Min(1) @Max(31) Integer birth_day,    // Valida se é entre 1 e 31
        @NotNull @Min(1) @Max(12) Integer birth_month,  // Valida se é entre 1 e 12
        @NotNull @Min(1900) @Max(2100) Integer birth_year  // Valida se é entre 1900 e 2100
) {
}
