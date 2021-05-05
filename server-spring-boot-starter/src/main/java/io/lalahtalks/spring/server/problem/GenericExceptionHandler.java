package io.lalahtalks.spring.server.problem;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;
import org.zalando.problem.spring.webflux.advice.ProblemHandling;
import org.zalando.problem.spring.webflux.advice.security.SecurityAdviceTrait;

@RestControllerAdvice
@Slf4j
public class GenericExceptionHandler implements ProblemHandling, SecurityAdviceTrait {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public final Problem handle(Exception e) {
        log.error("Internal server error", e);
        return Problem.valueOf(Status.INTERNAL_SERVER_ERROR);
    }

}
