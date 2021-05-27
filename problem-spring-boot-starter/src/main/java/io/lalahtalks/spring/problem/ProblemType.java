package io.lalahtalks.spring.problem;

import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

public interface ProblemType {

    URI getType();

    String getTitle();

    Status getHttpStatus();

    default Problem toProblem(String detail) {
        return Problem.builder()
                .withType(getType())
                .withTitle(getTitle())
                .withStatus(getHttpStatus())
                .withDetail(detail)
                .build();
    }

    default Problem toProblem() {
        return toProblem(null);
    }

}
