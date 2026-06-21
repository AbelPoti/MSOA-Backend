package edu.bbte.replate.image.service.impl;

import edu.bbte.replate.image.service.JwtService;
import edu.bbte.replate.image.utils.JwtKeyProviderPublicOnly;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Autowired
    private JwtKeyProviderPublicOnly jwtKeyProvider;

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    @Override
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);

        return claims.get(
                "roles",
                List.class
        );
    }

    @Override
    public String extractUserId(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String extractUsernameClaim(String token) {
        return extractAllClaims(token)
                .get("username", String.class);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(jwtKeyProvider.getPublicKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // Validate the token for expiration
    // Only do this because the app trusts the Auth service's tokens
    @Override
    public boolean validateToken(String token) {
        log.info("Validating Jwt token.");
        try {
            Claims claims = extractAllClaims(token);
            return !claims.getExpiration().before(new Date());
        } catch (JwtException e) {
            return false;
        }
    }
}
