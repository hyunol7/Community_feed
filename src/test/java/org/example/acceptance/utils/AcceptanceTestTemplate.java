package org.example.acceptance.utils;

import org.example.auth.application.dto.LoginRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;

import static org.example.acceptance.steps.LoginAcceptanceSteps.requestLoginGetTest;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AcceptanceTestTemplate {

    @Autowired
    private DatabaseCleanUp cleanUp;

    @Autowired
    private DataLoader loader;

    @BeforeEach
    public void init(){
        cleanUp.execute();
        loader.loadData();
    }

    protected void cleanUp(){
        cleanUp.execute();
    }

    protected String getEmailToken(String email){
        return loader.getEmailToken(email);
    }

    protected boolean isEmailVerified(String email){
        return loader.isEmailVerified(email);
    }

    protected Long getUserId(String email){
        return loader.getUserId(email);
    }

    protected void createUser(String email){
        loader.createUser(email);
    }

    protected String login(String email){
        return requestLoginGetTest(new LoginRequestDto(email, "password"));
    }


}


