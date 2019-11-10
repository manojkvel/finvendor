package com.finvendor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@ComponentScan(basePackages = {"com.finvendor","com.finvendor.api"})
@Configuration
public class TestBeanConfig {

    @Bean
    public Properties finvendorProperties(){
        return new Properties();
    }
}
