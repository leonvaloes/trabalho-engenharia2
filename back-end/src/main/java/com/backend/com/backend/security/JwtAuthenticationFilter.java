package com.backend.com.backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Logger;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwdDecoder jwtDecoder;
    private final JwtPrincipalConverter jwtPrincipalConverter;
    private static final Logger LOGGER = Logger.getLogger(JwtAuthenticationFilter.class.getName());

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        LOGGER.info("Starting JWT filter");
        extractTokenFromRequest(request)
                .map(token -> {
                    LOGGER.info("Token found: " + token);
                    return jwtDecoder.decode(token);
                })
                .map(jwtPrincipalConverter::convert)
                .map(UserPrincipalAuthentication::new)
                .ifPresent(authentication -> {
                    LOGGER.info("Setting authentication in SecurityContextHolder: " + authentication);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                });

        filterChain.doFilter(request, response);
        LOGGER.info("Filter chain continued");
    }

    private Optional<String> extractTokenFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        LOGGER.info("Extracting token from request: " + token);
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            return Optional.of(token.substring(7));
        }
        return Optional.empty();
    }
}
