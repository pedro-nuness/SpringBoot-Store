package com.hype.application.dto.auth;

public record LoginResponseDTO (
        String userId,
        String accessToken,
        String refreshToken
){
}
