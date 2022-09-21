package com.project.atlassian.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.atlassian.common.RequestBuilders;
import com.project.atlassian.mocks.WiremockSetup;
import com.project.atlassian.providers.ApiResponse;
import com.project.atlassian.validations.ValidateResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = {RunnerTest.class})
@ComponentScan(basePackages= {"com.project.atlassian"})
@Slf4j
public class RunnerTest extends AbstractTestNGSpringContextTests {
    @Autowired
    public RequestBuilders requestBuilders;
    @Autowired
    public ApiResponse apiResponse;
    @Autowired
    public ValidateResponse validateResponse;
    public static Response response;
    @Autowired
    public WiremockSetup wiremockSetup;

    @BeforeClass
    public void setUp(){
        wiremockSetup.mockServerInit();
        requestBuilders.setBaseURIs();
        requestBuilders.setContentTypes();
    }

    @Test
    public void createUser() throws JsonProcessingException {
        requestBuilders.setBasePaths("api/users");
        requestBuilders.setRequestBody("Subhajit","Engineer");
        response=apiResponse.getResponse("post");
        validateResponse.verifyStatusCode(201,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 1)
    public void updateUser() throws JsonProcessingException {
        requestBuilders.setBasePaths("api/users/2");
        requestBuilders.setRequestBody("Subhajit","Engineer");
        response=apiResponse.getResponse("put");
        validateResponse.verifyStatusCode(200,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 2)
    public void getUser(){
        requestBuilders.setBasePaths("api/users/2");
        response=apiResponse.getResponse("get");
        validateResponse.verifyStatusCode(200,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 3)
    public void deleteUser(){
        requestBuilders.setBasePaths("api/users/2");
        response=apiResponse.getResponse("delete");
        validateResponse.verifyStatusCode(204,response.statusCode());
        log.info(response.asPrettyString());
    }
}
