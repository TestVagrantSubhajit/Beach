package com.project.beach.init;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.project.beach.common.RequestBuilders;
import com.project.beach.mocks.WiremockSetup;
import com.project.beach.providers.ApiResponse;
import com.project.beach.tasks.HealthCheck;
import com.project.beach.validations.ValidateResponse;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.project.beach.constants.Generic.FOUNDATION_URL;
import static com.project.beach.constants.Generic.MOCK_URLS;

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
    @Autowired
    public HealthCheck healthCheck;

    @BeforeClass
    public void setUp(){
        if(healthCheck.verifyHealth(FOUNDATION_URL)){
            requestBuilders.setBaseURIs(FOUNDATION_URL);
        }else {
            wiremockSetup.mockServerInit();
            requestBuilders.setBaseURIs(MOCK_URLS);
        }
        requestBuilders.setContentTypes();
    }

    @Test(description = "Verify that API consumer should able to create user using POST[api/users]")
    public void createUser() throws JsonProcessingException {
        requestBuilders.setBasePaths("api/users");
        requestBuilders.setRequestBody("Subhajit","Engineer");
        response=apiResponse.getResponse("post");
        validateResponse.verifyStatusCode(201,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 1,description = "Verify that API consumer should able to update user using PUT[api/users/2]")
    public void updateUser() throws JsonProcessingException {
        requestBuilders.setBasePaths("api/users/2");
        requestBuilders.setRequestBody("Subhajit","Engineer");
        response=apiResponse.getResponse("put");
        validateResponse.verifyStatusCode(200,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 2,description = "Verify that API consumer should able to get user details using GET[api/users/2]")
    public void getUser(){
        requestBuilders.setBasePaths("api/users/2");
        response=apiResponse.getResponse("get");
        validateResponse.verifyStatusCode(200,response.statusCode());
        log.info(response.asPrettyString());
    }

    @Test(priority = 3,description = "Verify that API consumer should able to delete user using DELETE[api/users/2]")
    public void deleteUser(){
        requestBuilders.setBasePaths("api/users/2");
        response=apiResponse.getResponse("delete");
        validateResponse.verifyStatusCode(204,response.statusCode());
        log.info(response.asPrettyString());
    }

    @AfterClass
    public void tearDown(){
        wiremockSetup.mockServerShutdown();
    }
}
