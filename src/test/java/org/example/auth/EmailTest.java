package org.example.auth;

import org.example.auth.domain.Email;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmailTest {

    @ParameterizedTest
    @NullAndEmptySource
    void givenEmailIsEmpty_whenCreate_thenThrowError(String email){
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid/@ab", "naver.com", "natty#@naver", "안녕하세요.com"})
    void givenInvalidEmail_whenCreate_thenThrowError(String email){
        assertThrows(IllegalArgumentException.class, () -> Email.createEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"valid@ab", "a@naver.com", "natty@naver", "test@test.com"})
    void givenEmailValidWhenCreateThenReturnEmail(String email){
        //given

        //when
        Email emailValue = Email.createEmail(email);

        //then
        assertEquals(email, emailValue.getEmailText());
    }
}
