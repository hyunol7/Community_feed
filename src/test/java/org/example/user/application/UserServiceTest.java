package org.example.user.application;

import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.example.fake.FakeObjectFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();

    @Test
    void givenUser_whenFindByUserUser_thenCanfindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User saveUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(saveUser.getId());
        UserInfo userInfo = foundUser.getUserInfo();
        assertEquals(foundUser.getId(), saveUser.getId());
        assertEquals("test", userInfo.getName());
    }
}
