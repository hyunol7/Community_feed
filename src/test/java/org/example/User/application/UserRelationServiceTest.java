package org.example.User.application;

import org.example.User.Repository.FakeUserRelationRepository;
import org.example.User.Repository.FakeUsreRepository;
import org.example.User.application.Interface.UserRelationRepository;
import org.example.User.application.Interface.UserRepository;
import org.example.User.application.dto.CreateUserRequestDto;
import org.example.User.application.dto.FollowUserRequestDto;
import org.example.User.domain.User;
import org.example.fake.FakeObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserRelationServiceTest {

    private final UserService userService = FakeObjectFactory.getUserService();
    private final UserRelationService userRelationService = FakeObjectFactory.getUserRelationService();

    private User user1;
    private User user2;

    private FollowUserRequestDto requestDto;

    @BeforeEach
    void init(){
        CreateUserRequestDto dto = new CreateUserRequestDto("test", "");
                this.user1 =userService.createUser(dto);
                this.user2 =userService.createUser(dto);

                this.requestDto = new FollowUserRequestDto(user1.getId(), user2.getId());
        }

        @Test
        void givenCreateTwoUser_whenFollow_thenUserFollowSaved(){

            //when
            userRelationService.follow(requestDto);

            //then
            assertEquals(1, user1.followingCount());
            assertEquals(1, user2.followerCount());
    }

        @Test
        void givenCreateTwoUserFollowed_whenFollow_thenUserThrowError(){
        // given
        userRelationService.follow(requestDto);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(requestDto));
    }

        @Test
        void givenCreateOneUser_whenFollow_thenUserThrowError(){
        //given
        FollowUserRequestDto saveUser = new FollowUserRequestDto(user1.getId(), user1.getId());

        //when, then
            assertThrows(IllegalArgumentException.class, () -> userRelationService.follow(saveUser));
    }

    @Test
    void givenCreateTwoUserFollow_whenUnFollow_thenUserUnFollowSaved(){
        //given
        userRelationService.follow(this.requestDto);

        //when
       // userRelationService.unfollow(this.requestDto);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(1, user2.followerCount());
    }

    @Test
    void givenCreateTwoUser_whenUnFollow_thenUserThrowError(){

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(this.requestDto));
    }

    @Test
    void givenCreateUser_whenFollowSelf_thenUserThrowError(){
        //given
        FollowUserRequestDto saveUser = new FollowUserRequestDto(user1.getId(), user2.getId());

        //when, then
        assertThrows(IllegalArgumentException.class, () -> userRelationService.unfollow(saveUser));
    }
}
