package io.lalahtalks.spring.server.security;

import org.springframework.security.config.web.server.ServerHttpSecurity;

public interface HttpSecurityAdapter {

    ServerHttpSecurity configure(ServerHttpSecurity http);

}
