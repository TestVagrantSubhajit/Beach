package com.project.atlassian.validations;

import org.springframework.stereotype.Component;
import org.testng.Assert;

@Component
public class ValidateResponse {
    public void verifyStatusCode(int expectedStatusCode,int actualStatusCode){
        Assert.assertEquals(actualStatusCode,expectedStatusCode);
    }
}
