package org.example.auth;

import org.example.auth.domain.TokenProvider;
import org.junit.jupiter.api.Test;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TokenProviderTest {

    private final String secret = "testestestestsetestestetsetstestetsetetetstestesetsetetsttstetet";
    private final TokenProvider tokenProvider = new TokenProvider(secret);

    @Test
    void givenValidAndRole_whenCreateToken_thenReturnValidToken() {
        //given
        Long userId = 1L;
        String role = "ADMIN";

        //when
        String token = tokenProvider.createToken(userId, role);

        //then
        assertNotNull(token);
        assertEquals(userId, tokenProvider.getUserId(token));
        assertEquals(role, tokenProvider.getUserRole(token));
    }
    @Test
    void givenInvalidToken_whenGetUserId_thenThrowError(){
        //given
        String invalidToken = "invalidToken";

        assertThrows(Exception.class, () -> tokenProvider.getUserRole(invalidToken));
    }
}
