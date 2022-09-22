package com.project.beach.providers;

import com.project.beach.common.RequestBuilders;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ApiResponse {
    @Autowired
    public RequestBuilders requestBuilders;
    public static Response response;
    public Response getResponse(String httpMethod){
        response= httpMethod.equalsIgnoreCase("post")?RestAssured.given(requestBuilders.buildRequestSpecs()).when().post()
                    :httpMethod.equalsIgnoreCase("get")?RestAssured.given(requestBuilders.buildRequestSpecs()).when().get()
                    :httpMethod.equalsIgnoreCase("delete")?RestAssured.given(requestBuilders.buildRequestSpecs()).when().delete()
                    :httpMethod.equalsIgnoreCase("put")?RestAssured.given(requestBuilders.buildRequestSpecs()).when().put()
                    :null;
        assert response != null;
        return response;
    }
}
