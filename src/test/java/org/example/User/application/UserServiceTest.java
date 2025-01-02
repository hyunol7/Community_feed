package org.example.User.application;

import org.example.User.Repository.FakeUsreRepository;
import org.example.User.application.Interface.UserRepository;
import org.example.User.application.dto.CreateUserRequestDto;
import org.example.User.domain.User;
import org.example.User.domain.UserInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest {

    private final UserRepository userRepository = new FakeUsreRepository();
    private final UserService userService = new UserService(userRepository);

    @Test
    void givenUser_whenFindByUserUser_thenCanfindUser() {
        //given
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");

        //when
        User saveUser = userService.createUser(dto);

        //then
        User foundUser = userService.getUser(saveUser.getId());
        UserInfo userInfo = foundUser.getInfo();
        assertEquals(foundUser.getId(), saveUser.getId());
        assertEquals("test", "");
    }
}
