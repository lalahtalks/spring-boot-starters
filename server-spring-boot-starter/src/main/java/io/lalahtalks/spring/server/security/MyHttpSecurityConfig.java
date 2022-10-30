package io.lalahtalks.spring.server.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.zalando.problem.spring.webflux.advice.security.SecurityProblemSupport;

import java.util.List;

@Configuration
@EnableWebFluxSecurity
public class MyHttpSecurityConfig {

    private final HttpSecurityAdapter adapter;
    private final SecurityProblemSupport problemSupport;

    public MyHttpSecurityConfig(HttpSecurityAdapter adapter, SecurityProblemSupport problemSupport) {
        this.adapter = adapter;
        this.problemSupport = problemSupport;
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        var configuration = new CorsConfiguration();
        configuration.setAllowedOriginPatterns(List.of("*"));
        configuration.setAllowedMethods(List.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(List.of("Authorization", "Cache-Control", "Content-Type"));

        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, MyJwtAuthenticationConverter converter) {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(problemSupport)
                .accessDeniedHandler(problemSupport)
                .and()
                .oauth2ResourceServer()
                .jwt()
                .jwtAuthenticationConverter(converter)
                .and();

        return adapter.configure(http)
                .build();
    }

}
