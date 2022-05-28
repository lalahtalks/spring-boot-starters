package io.lalahtalks.spring.server.security;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class MyJwtAuthenticationConverter implements Converter<Jwt, Mono<JwtAuthenticationToken>> {

    private static final String SCOPE_PREFIX = "SCOPE_";
    private static final String CLAIM_SCOPE = "scope";

    @Override
    public Mono<JwtAuthenticationToken> convert(Jwt jwt) {
        var authorities = extractAuthorities(jwt);
        var token = new JwtAuthenticationToken(jwt, authorities);
        return Mono.just(token);
    }

    private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
        return extractScopes(jwt)
                .collect(Collectors.toList());
    }

    private Stream<GrantedAuthority> extractScopes(Jwt jwt) {
        if (jwt.hasClaim(CLAIM_SCOPE)) {
            return Arrays.stream(jwt.getClaimAsString(CLAIM_SCOPE).split(" "))
                    .map(it -> new SimpleGrantedAuthority(SCOPE_PREFIX + it));
        }

        return Stream.empty();
    }

}
