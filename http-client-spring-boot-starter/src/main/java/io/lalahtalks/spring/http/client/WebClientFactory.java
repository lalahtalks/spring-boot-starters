package io.lalahtalks.spring.http.client;

import io.lalahtalks.spring.http.client.log.LoggingFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.InMemoryReactiveOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
@RequiredArgsConstructor
public class WebClientFactory {

    private static final String REALM_LALAHTALKS = "lalahtalks";

    private final ReactiveClientRegistrationRepository clientRegistrations;
    private final LoggingFilter loggingFilter;
    private final WebClient.Builder webClientBuilder;

    public WebClient create(HttpApiClientProperties properties) {
        var inMemory = new InMemoryReactiveOAuth2AuthorizedClientService(clientRegistrations);
        var authorizedClientManager = new AuthorizedClientServiceReactiveOAuth2AuthorizedClientManager(clientRegistrations, inMemory);
        var oauth2Filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(authorizedClientManager);
        oauth2Filter.setDefaultClientRegistrationId(REALM_LALAHTALKS);

        return webClientBuilder
                .baseUrl(properties.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .filter(oauth2Filter)
                .filter(loggingFilter)
                .build();
    }

}
