package io.lalahtalks.spring.http.client.log;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class LoggingWebClientCustomizer implements WebClientCustomizer {

    private final LoggingFilter loggingFilter;

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder.filter(loggingFilter);
    }

}
