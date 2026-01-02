package com.anmol.vroom.security.jwt.impl;

import com.anmol.vroom.security.jwt.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;

@Service
public class JwtServiceImpl implements JwtService {

    private final String secret;
    private final long expirationTime;

    public JwtServiceImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration}") long expirationTime
    ) {
        this.secret = secret;
        this.expirationTime = expirationTime;
    }

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    @Override
    public String generateToken(Long userId, String role){
        return Jwts.builder()
                .subject(String.valueOf(userId))
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSecretKey())
                .compact();
    }

    @Override
    public boolean validateToken(String token){
        try{
            extractAllClaims(token);
            return true;
        }
        catch(Exception ex){
            return false;
        }
    }

    @Override
    public Long extractUserId(String token){
        return Long.parseLong(extractAllClaims(token).getSubject());
    }

    @Override
    public String extractRole(String token){
        return extractAllClaims(token).get("role", String.class);
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}
