package com.project.beach.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.builder.RequestSpecBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RequestSpecs {
    @Bean
    public RequestSpecBuilder requestSpecBuilder(){
        return new RequestSpecBuilder();
    }
    @Bean
    public ObjectMapper objectMapper(){
        return new ObjectMapper();
    }
}
