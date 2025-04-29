package com.hype.application.service.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.hype.application.domain.Authentication.RefreshToken;
import com.hype.application.domain.user.User;

import com.hype.application.exception.EventErrorTokenGenerationException;
import com.hype.application.infra.Security.TokenExpirationTime;
import com.hype.application.infra.Security.TokenType;
import com.hype.application.repository.auth.RefreshTokenRepository;
import jakarta.transaction.Transactional;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secretKey;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    public String generateAccessToken(User user) {
        return generateToken(user, genAccessTokenExpirationDate(), TokenType.ACCESS.getClaimValue());
    }

    public String generateRefreshToken(User user) {
        return generateToken(user, genRefreshTokenExpirationDate(), TokenType.REFRESH.getClaimValue());
    }

    private Instant genAccessTokenExpirationDate(){
        return LocalDateTime.now().plusHours(TokenExpirationTime.ACCESS.getHours()).toInstant(ZoneOffset.of("-03:00"));
    }

    private Instant genRefreshTokenExpirationDate(){
        return LocalDateTime.now().plusDays(TokenExpirationTime.REFRESH.getHours()).toInstant(ZoneOffset.of("-03:00"));
    }

    private String generateToken(User user, Instant ExpirationInstant, String Type) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            String token = JWT.create()
                    .withIssuer("auth-api")
                    .withClaim("type", Type)
                    .withSubject(user.getEmail())
                    .withExpiresAt(ExpirationInstant)
                    .sign(algorithm);

            if(Type.equals(TokenType.REFRESH.getClaimValue()) ){
                saveRefreshToken(token, user);
            }

            return token;
        }
        catch(JWTCreationException e){
            throw new EventErrorTokenGenerationException();
        }
    }

    @Transactional
    public boolean validateAndInvalidateToken(String token) {
        String tokenHash = hashToken(token);

        return  refreshTokenRepository.findByTokenHashAndActiveTrue(tokenHash)
                .map(rt -> {
                    refreshTokenRepository.delete(rt);
                    return true;
                })
                .orElse(false);
    }

    public String ValidateToken(String token, String expectedType) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .withClaim("type", expectedType)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

    private void saveRefreshToken(String token, User user) {
        String tokenHash = hashToken(token);
        RefreshToken refreshToken = new RefreshToken(user, tokenHash);
        refreshTokenRepository.save(refreshToken);
    }

    private String hashToken(String token) {
        return DigestUtils.sha256Hex(token);
    }
}
