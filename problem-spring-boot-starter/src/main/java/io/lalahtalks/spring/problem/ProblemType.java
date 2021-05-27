package io.lalahtalks.spring.problem;

import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;

public interface ProblemType {

    URI getType();

    String getTitle();

    int getHttpStatusCode();

    default Problem toProblem(String detail) {
        var status = Status.valueOf(getHttpStatusCode());
        return Problem.builder()
                .withType(getType())
                .withTitle(getTitle())
                .withStatus(status)
                .withDetail(detail)
                .build();
    }

    default Problem toProblem() {
        return toProblem(null);
    }

}
