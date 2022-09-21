package com.project.atlassian.tasks;

import io.restassured.RestAssured;
import org.springframework.stereotype.Component;

@Component
public class HealthCheck {
    public boolean verifyHealth(String foundationUrl){
        int response= RestAssured.given()
                .baseUri(foundationUrl)
                .basePath("api/health")
                .when()
                .get().statusCode();
        return response == 200;
    }
}
