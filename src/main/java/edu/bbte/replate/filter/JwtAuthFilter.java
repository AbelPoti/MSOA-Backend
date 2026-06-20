package edu.bbte.replate.filter;

import edu.bbte.replate.service.JwtService;
import edu.bbte.replate.utils.JwtPrincipal;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@Slf4j
public class JwtAuthFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain
    ) throws ServletException, IOException {
        log.info("Filtering user request for JWT token.");
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            try {
                String token = authorizationHeader.substring(7);

                log.info("Validating JWT.");

                if (jwtService.validateToken(token)) {
                    log.info("JWT is valid, setting authentication in security context.");

                    String userId = jwtService.extractUserId(token);

                    String username = jwtService.extractUsernameClaim(token);

                    List<String> roles =
                            jwtService.extractRoles(token);

                    List<SimpleGrantedAuthority> authorities =
                            roles.stream()
                                    .map(SimpleGrantedAuthority::new)
                                    .toList();

                    // Create a custom principal through which authorization will be the same flow as if it was done
                    // From a UserDetails object
                    JwtPrincipal principal =
                            new JwtPrincipal(
                                    Long.parseLong(userId),
                                    username
                            );

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    principal,
                                    null,
                                    authorities);

                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    log.warn("Invalid JWT token.");
                    SecurityContextHolder.clearContext();
                }
            } catch (JwtException | IllegalArgumentException e) {
                log.warn("Invalid JWT token: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        } else {
            log.info("No authorization header is set in the request.");
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
