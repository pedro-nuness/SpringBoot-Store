package com.hype.application.repository.auth;


import com.hype.application.domain.Authentication.RefreshToken;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByTokenHashAndActiveTrue(String tokenHash);

    @Transactional
    @Modifying
    @Query("DELETE FROM RefreshToken rt WHERE rt.active = false AND rt.createdAt < :expirationThreshold")
    int deleteInactiveTokensOlderThan(LocalDateTime expirationThreshold);

    void deleteByTokenHash(String tokenHash);
}
