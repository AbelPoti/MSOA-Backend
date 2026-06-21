package edu.bbte.replate.core.utils;

public record JwtPrincipal(
        Long userId,
        String username,
        String token
) {
    public static final String ADMIN_AUTHORITY_NAME = "ADMIN";
    public static final String USER_AUTHORITY_NAME = "USER";
}
