package edu.bbte.replate.service;

import org.springframework.security.core.Authentication;

public interface JwtService {
    String generateToken(Authentication authentication);

    String extractUsername(String token);
}
