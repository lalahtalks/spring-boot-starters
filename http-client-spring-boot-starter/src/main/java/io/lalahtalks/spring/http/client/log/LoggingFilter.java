package io.lalahtalks.spring.http.client.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Component
public class LoggingFilter implements ExchangeFilterFunction {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingFilter.class);

    @Override
    public Mono<ClientResponse> filter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
        var start = System.currentTimeMillis();

        return exchangeFunction.exchange(clientRequest)
                .doOnNext(response -> {
                    var elapsed = System.currentTimeMillis() - start;
                    LOGGER.info(
                            "Called {} {} - returned {} in {} ms",
                            clientRequest.method(),
                            clientRequest.url(),
                            response.rawStatusCode(),
                            elapsed);
                });
    }

}
