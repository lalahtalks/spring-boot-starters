package io.lalahtalks.spring.http.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class LoggingFilter implements ExchangeFilterFunction {

    @Override
    public Mono<ClientResponse> filter(ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
        var start = System.currentTimeMillis();

        return exchangeFunction.exchange(clientRequest)
                .doOnNext(response -> {
                    var elapsed = System.currentTimeMillis() - start;
                    log.info(
                            "Called {} {} - returned {} in {} ms",
                            clientRequest.method(),
                            clientRequest.url(),
                            response.rawStatusCode(),
                            elapsed);
                });
    }

}
