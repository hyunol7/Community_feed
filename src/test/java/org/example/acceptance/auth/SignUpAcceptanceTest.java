package org.example.acceptance.auth;

import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.example.auth.application.interfaces.EmailSendRepository;
import org.example.auth.application.interfaces.EmailVerificationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.example.acceptance.steps.SignUpAcceptanceSteps.requestSendEmail;
import static org.example.acceptance.steps.SignUpAcceptanceSteps.requestVerifyEmail;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class SignUpAcceptanceTest extends AcceptanceTestTemplate {

    private final String email = "email@email.com";

    @BeforeEach
    void setUp() {
        this.cleanUp();
    }


    @Test
    void givenEmail_whenSendEmail_thenVerificationTokenSaved(){
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto(email);

        //when
        Integer code = requestSendEmail(dto);

        //then

        String token = this.getEmailToken(email);
        assertNotNull(token);
        assertEquals(0, code);


    }

    @Test
    void givenInvalidEmail_whenEmailSend_thenVerificationTokenNotSaved() {
        //given
        SendEmailRequestDto dto = new SendEmailRequestDto("abcd");

        //when
        Integer code = requestSendEmail(dto);

        //then
        assertEquals(400, code);
    }


    @Test
    void givenSendEmail_whenVerifyEmail_thenEmailVerified(){
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        String token = getEmailToken(email);
        Integer code = requestVerifyEmail(email, token);

        //then
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(0, code);
        assertTrue(isEmailVerified);
    }

    @Test
    void givenSendEmail_whenVerifyEmailWithWrongToken_thenEmailNotVerified(){
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail(email, "wrong token");

        //when
        boolean isEmailVerified = isEmailVerified(email);
        assertEquals(400, code);
        assertTrue(isEmailVerified);
    }

    @Test
    void givenSendEmailVerified_whenVerifyAgain_thenThrowError(){
        //given
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);

        //when
        Integer code = requestVerifyEmail(email, token);

        //then
        assertEquals(400, code);
    }

    @Test
    void givenSenEmail_whenVerifyEmailWithWrongEmail_thenThrowError(){
        //given
        requestSendEmail(new SendEmailRequestDto(email));

        //when
        Integer code = requestVerifyEmail("wrong email", "token");

        //then
        assertEquals(400, code);

    }

}
