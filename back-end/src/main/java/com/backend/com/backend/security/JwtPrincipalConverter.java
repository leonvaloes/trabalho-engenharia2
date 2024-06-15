package com.backend.com.backend.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class JwtPrincipalConverter {
    public UserPrincipal convert(DecodedJWT jwt){
        return UserPrincipal.builder()
                .UserId(Long.valueOf(jwt.getSubject()))
                .login(jwt.getClaim("e").asString())
                .authorities(extractAutoritiesFromClaim(jwt))
                .build();
    }

    private List<SimpleGrantedAuthority> extractAutoritiesFromClaim(DecodedJWT jwt){
        var claim = jwt.getClaim("a");
        if (claim.isNull() || claim.isMissing()) return List.of();
        return claim.asList(SimpleGrantedAuthority.class);

    }
}
