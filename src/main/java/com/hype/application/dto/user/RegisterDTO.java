package com.hype.application.dto.user;

import jakarta.validation.constraints.NotEmpty;

public record RegisterDTO (@NotEmpty String login,
                           @NotEmpty String password) {
}
