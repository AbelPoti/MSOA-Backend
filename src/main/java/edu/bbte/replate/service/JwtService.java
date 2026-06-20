package edu.bbte.replate.service;

import java.util.List;

public interface JwtService {
    boolean validateToken(String token);

    List<String> extractRoles(String token);

    String extractUserId(String token);

    String extractUsernameClaim(String token);
}
