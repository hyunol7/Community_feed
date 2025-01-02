package org.example.User.domein;

import org.example.User.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserInfoTest {

    @Test
    void givenAndNameProfileImage_whenCreated_thenThrowNothing(){
        //given
        String name = "name";
        String profileImageUrl = "";
        //when

        //then
        assertDoesNotThrow(() -> new UserInfo(name, profileImageUrl));
        }

        @Test
    void givenAndNameProfileImage_whenCreated_thenThrowError(){
            //given
            String name = "";
            String profileImageUrl = "";
            //when

            //then

            assertThrows(IllegalArgumentException.class,() -> new UserInfo(name, profileImageUrl));
        }
    }



