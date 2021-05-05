package io.lalahtalks.spring.http.client;

import java.net.URI;

public final class UnknownException extends RuntimeException {

    public static UnknownException of(URI type, String detail) {
        var message = type.toString() + ": " + detail;
        return new UnknownException(message);
    }

    public UnknownException(String message) {
        super(message);
    }

}
