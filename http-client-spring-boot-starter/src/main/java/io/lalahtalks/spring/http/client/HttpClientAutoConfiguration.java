package io.lalahtalks.spring.http.client;

import io.lalahtalks.spring.http.client.log.LoggingFilter;
import io.lalahtalks.spring.http.client.log.LoggingWebClientCustomizer;
import io.lalahtalks.spring.problem.ProblemAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@AutoConfigureAfter(ProblemAutoConfiguration.class)
public class HttpClientAutoConfiguration {

    @Bean
    public WebClientCustomizer loggingWebClientCustomizer() {
        return new LoggingWebClientCustomizer(new LoggingFilter());
    }

}
