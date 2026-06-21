package edu.bbte.replate.image.utils;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Configuration
public class FeignJwtConfiguration {
    @Bean
    public RequestInterceptor jwtForwardingInterceptor() {
        return requestTemplate -> {

            Authentication authentication =
                    SecurityContextHolder.getContext().getAuthentication();

            if (authentication == null) {
                return;
            }

            JwtPrincipal principal = (JwtPrincipal) authentication.getPrincipal();

            assert principal != null;

            requestTemplate.header(
                    HttpHeaders.AUTHORIZATION,
                    "Bearer " + principal.token()
            );
        };
    }
}
