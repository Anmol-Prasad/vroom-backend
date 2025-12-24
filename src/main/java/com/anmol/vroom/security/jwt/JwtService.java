package com.anmol.vroom.security.jwt;

public interface JwtService {
    String generateToken(Long userId, String role);

    boolean validateToken(String token);

    Long extractUserId(String token);

    String extractRole(String token);
}
