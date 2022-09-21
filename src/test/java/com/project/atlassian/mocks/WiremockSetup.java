package com.project.atlassian.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.stereotype.Component;

import static com.github.tomakehurst.wiremock.client.WireMock.equalToJson;

@Component
public class WiremockSetup {
    public static final String MOCK_HOST="localhost";
    public static final int MOCK_PORT=5645;
    public static WireMockServer server=new WireMockServer(MOCK_PORT);
    public ResponseDefinitionBuilder responseDefinitionBuilder;

    public void mockServerInit(){
        server.start();
        WireMock.configureFor(MOCK_HOST,MOCK_PORT);
    }

    public void mockForCreateUser(){
        responseDefinitionBuilder=new ResponseDefinitionBuilder();
        responseDefinitionBuilder.withStatus(201);
        responseDefinitionBuilder.withHeader("Content-Type","text/json");
        responseDefinitionBuilder.withBody("Create User");
        WireMock.givenThat(WireMock.post("/api/users").withRequestBody(equalToJson("{\n" +
                "    \"name\": \"Subhajit\",\n" +
                "    \"job\": \"Engineer\"\n" +
                "}")).willReturn(responseDefinitionBuilder));
    }

    public void mockForUpdateUser(){
        responseDefinitionBuilder=new ResponseDefinitionBuilder();
        responseDefinitionBuilder.withStatus(200);
        responseDefinitionBuilder.withHeader("Content-Type","text/json");
        responseDefinitionBuilder.withBody("Update User");
        WireMock.givenThat(WireMock.put("/api/users/2").withRequestBody(equalToJson("{\n" +
                "    \"name\": \"Subhajit\",\n" +
                "    \"job\": \"Engineer\"\n" +
                "}")).willReturn(responseDefinitionBuilder));
    }

    public void mockForGetUser(){
        responseDefinitionBuilder=new ResponseDefinitionBuilder();
        responseDefinitionBuilder.withStatus(200);
        responseDefinitionBuilder.withHeader("Content-Type","text/json");
        responseDefinitionBuilder.withBody("Get User");
        WireMock.givenThat(WireMock.get("/api/users/2").willReturn(responseDefinitionBuilder));
    }

    public void mockForDeleteUser(){
        responseDefinitionBuilder=new ResponseDefinitionBuilder();
        responseDefinitionBuilder.withStatus(204);
        responseDefinitionBuilder.withHeader("Content-Type","text/json");
        responseDefinitionBuilder.withBody("Delete User");
        WireMock.givenThat(WireMock.delete("/api/users/2").willReturn(responseDefinitionBuilder));
    }
}
