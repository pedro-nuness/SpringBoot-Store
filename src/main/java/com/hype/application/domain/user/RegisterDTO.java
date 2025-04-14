package com.hype.application.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record RegisterDTO (@NotEmpty String login,
                           @NotEmpty String password) {
}
