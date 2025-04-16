package org.example.acceptance.auth;

import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.auth.application.dto.CreateUserAuthRequestDto;
import org.example.auth.application.dto.LoginRequestDto;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.example.acceptance.steps.LoginAcceptanceSteps.requestLoginGetTest;
import static org.example.acceptance.steps.SignUpAcceptanceSteps.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;

public class LoginAcceptanceTest extends AcceptanceTestTemplate {
    private final String email = "email@email.com";

    @BeforeEach
    void setUp(){
        this.cleanUp();
        this.createUser(email);
    }

    @Test
    void givenEmailAndPassword_whenLogin_thenReturnToken(){
        //given
        LoginRequestDto loginRequest = new LoginRequestDto(email, "password");
        
        //when
        String token = requestLoginGetTest(loginRequest);

        //then
        assertNotNull(token);
    }

    @Test
    void givenEmailAndWrongPassword_whenLogin_thenReturnCodeNotZero(){
        //given
        LoginRequestDto loginRequest = new LoginRequestDto(email, "wrong password");

        //when
        String token = requestLoginGetTest(loginRequest);

        //then
        assertNotNull(token);
    }



}
