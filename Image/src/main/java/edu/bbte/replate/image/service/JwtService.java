package edu.bbte.replate.image.service;

import java.util.List;

public interface JwtService {
    boolean validateToken(String token);

    List<String> extractRoles(String token);

    String extractUserId(String token);

    String extractUsernameClaim(String token);
}
