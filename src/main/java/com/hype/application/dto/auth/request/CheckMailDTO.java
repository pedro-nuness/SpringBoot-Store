package com.hype.application.dto.auth.request;

import jakarta.validation.constraints.NotEmpty;

public record CheckMailDTO (
        @NotEmpty String email
){
}
