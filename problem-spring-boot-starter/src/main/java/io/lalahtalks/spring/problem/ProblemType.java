package io.lalahtalks.spring.problem;

import java.net.URI;

public interface ProblemType {

    int getHttpStatusCode();

    URI getType();

}
