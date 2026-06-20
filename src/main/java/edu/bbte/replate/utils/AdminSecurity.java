package edu.bbte.replate.utils;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component("adminSecurity")
public class AdminSecurity {
    public boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            JwtPrincipal principal = (JwtPrincipal) authentication.getPrincipal();

            SimpleGrantedAuthority adminAuthority = new SimpleGrantedAuthority(JwtPrincipal.ADMIN_AUTHORITY_NAME);
            // Safe to assert since JwtAuthFilter treats user as unauthorized if principal is invalid/not set
            assert principal != null;
            return authentication.getAuthorities().contains(adminAuthority);
        } else {
            throw new AccessDeniedException("Access to resource is unauthorized.");
        }
    }
}
