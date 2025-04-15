package org.example.auth;

import org.example.auth.domain.Password;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PasswordTest {
    @Test
    void givenPassword_whenMatchSamePassword_thenReturnTrue() {
        Password password = Password.createPassword("password");

        assertTrue(password.matchPassword("password"));
    }

    @Test
    void givenPassword_whenMatchDifferentPassword_thenReturnTrue() {
        Password password = Password.createPassword("password");

        assertTrue(password.matchPassword("password"));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenPasswordIsNull_thenThrowError(String password) {
        assertThrows(IllegalArgumentException.class, () -> Password.createPassword(null));
    }
}
