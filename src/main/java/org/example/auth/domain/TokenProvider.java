package org.example.auth.domain;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
@Component
public class TokenProvider {

    private final SecretKey secretKey;
    private static final long TOKEN_VALID_TIME = 1000L * 60 * 60;

    public TokenProvider(@Value("${secret-key}") String secretKey) {
        this.secretKey = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String createToken(Long userId, String role) {
        Claims claims = Jwts.claims()
                .subject(userId.toString())
                .build();

        Date now = new Date();
        Date validTime = new Date(now.getTime() + TOKEN_VALID_TIME);
        return  Jwts.builder()
                .claims(claims)
                .issuedAt(now)
                .expiration(validTime)
                .claim("role", role)
                .signWith(secretKey)
                .compact();
    }

    public Long getUserId(String token) {
        return Long.parseLong(
                Jwts.parser()
                        .verifyWith(secretKey)
                        .build()
                        .parseSignedClaims(token)
                        .getPayload()
                        .getSubject()
        );
    }

    public String getUserRole(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }
}
