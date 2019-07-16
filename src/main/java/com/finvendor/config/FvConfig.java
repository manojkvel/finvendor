package com.finvendor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class FvConfig {

    /**
     * It’s important to note that enabling parameter validation also requires a MethodValidationPostProcessor bean.
     * If we’re using a Spring Boot application, then this bean is auto-configured is we have the
     * hibernate-validator dependency on our classpath.
     * <p>
     * Otherwise, in a standard Spring application, we have to add this bean explicitly:
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }
}


