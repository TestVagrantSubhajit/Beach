package com.project.atlassian.mocks;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.springframework.stereotype.Component;

@Component
public class WiremockSetup {
    public static final String MOCK_HOST="localhost";
    public static final int MOCK_PORT=5645;
    public static WireMockServer server=new WireMockServer(MOCK_PORT);

    public void mockServerInit(){
        server.start();
        WireMock.configureFor(MOCK_HOST,MOCK_PORT);
    }

    public void mockServerShutdown(){
        if(server.isRunning() && null !=server){
            server.shutdown();
        }
    }
}
