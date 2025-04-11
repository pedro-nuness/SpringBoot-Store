package com.hype.application.domain.user.useraddress;

import jakarta.validation.constraints.NotBlank;

public record UserAddressRequestDTO(
        @NotBlank
        String name,

        @NotBlank
        String cep,

        @NotBlank
        String uf,

        @NotBlank
        String city,

        @NotBlank
        String address
) {
}


