package io.lalahtalks.spring.http.client.log;

import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.web.reactive.function.client.WebClient;

public class LoggingWebClientCustomizer implements WebClientCustomizer {

    private final LoggingFilter loggingFilter;

    public LoggingWebClientCustomizer(LoggingFilter loggingFilter) {
        this.loggingFilter = loggingFilter;
    }

    @Override
    public void customize(WebClient.Builder webClientBuilder) {
        webClientBuilder.filter(loggingFilter);
    }

}
