package io.lalahtalks.spring.problem;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.ProblemModule;

@Configuration
public class ProblemAutoConfiguration {

    @Bean
    public ProblemModule problemModule() {
        return new ProblemModule();
    }

}
