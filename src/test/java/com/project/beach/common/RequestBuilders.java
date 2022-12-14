package com.project.beach.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.beach.models.User;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestBuilders {
    @Autowired
    public RequestSpecBuilder requestSpecBuilder;
    @Autowired
    public User user;
    @Autowired
    public ObjectMapper mapper;
    public void setContentTypes(){
        requestSpecBuilder.setContentType(ContentType.JSON);
    }
    public void setBaseURIs(String foundationUrl){
        requestSpecBuilder.setBaseUri(foundationUrl);
    }
    public void setBasePaths(String bashPath){
        requestSpecBuilder.setBasePath(bashPath);
    }
    public void setRequestBody(String name,String job) throws JsonProcessingException {
        user.setName(name);
        user.setJob(job);
        requestSpecBuilder.setBody(mapper.writeValueAsString(user));
    }
    public RequestSpecification buildRequestSpecs(){
        return requestSpecBuilder.build();
    }
}
