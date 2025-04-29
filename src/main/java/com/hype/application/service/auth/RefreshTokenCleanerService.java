package com.hype.application.service.auth;

import com.hype.application.infra.Security.TokenExpirationTime;
import com.hype.application.repository.auth.RefreshTokenRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class RefreshTokenCleanerService {

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenCleanerService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Scheduled(cron = "0 0 3 * * *") // 3 da manha
    public void cleanExpiredTokens() {
        LocalDateTime expirationThreshold = LocalDateTime.now().minusHours(TokenExpirationTime.REFRESH.getHours()); // Tokens com mais de 7 dias
        int deleted = refreshTokenRepository.deleteInactiveTokensOlderThan(expirationThreshold);
        System.out.println("Tokens limpos: " + deleted);
    }
}