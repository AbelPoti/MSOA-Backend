package edu.bbte.replate.utils;

public record JwtPrincipal(
        Long userId,
        String username
) {
    public static final String ADMIN_AUTHORITY_NAME = "ADMIN";
    public static final String USER_AUTHORITY_NAME = "USER";
}
