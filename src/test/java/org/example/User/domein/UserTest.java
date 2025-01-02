package org.example.User.domein;

import org.example.User.domain.User;
import org.example.User.domain.UserInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private final UserInfo userInfo = new UserInfo("Test", "");
    private User user1;
    private User user2 = new User(2L, userInfo);

    @BeforeEach
    void init(){
        user1 = new User(1L, userInfo);
        user2 = new User(2L, userInfo);
    }


    @Test
    void givenTwoUser_whenEqual_thenReturnFalse() {
        //when
        boolean isSame = user1.equals(user2);

        //then
        assertFalse(isSame);

    }

    @Test
    void givenTwoSameUser_whenEqual_thenReturnTrue() {
        //given
        User sameUser = new User(1L, userInfo);

        //when
        boolean isSame= user1.equals(sameUser);
        
        //then
        assertTrue(isSame);
    }

    @Test
    void givenTeoUser_whenHashcode_throwReturnFalse(){
        //when
        int hashCode1 = user1.hashCode();
        int hashCode2 = user2.hashCode();

        //then
        assertNotEquals(hashCode1, hashCode2);
    }

    @Test
    void givenTeoUser_whenHashcode_throwEqual(){
        //given
        User sameUser = new User(1L, userInfo);
        //when
        int hashCode1 = user1.hashCode();
        int sameUserHashcode = sameUser.hashCode();

        //then
        assertEquals(hashCode1, sameUserHashcode);
    }

    @Test
    void givenTwoUser_whenUser1FollowUser2_thenIncreaseUserCount(){
        // when
        user1.follow(user2);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(1, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
    @Test
    void givenTwoUser1FollowUser2_whenUnfollow_thenDecreaseUserCount(){
        //given
        user1.follow(user2);

        // when
        user1.unfollow(user2);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(1, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }

    @Test
    void givenTwoUser_whenUnfollow_thenNotDecreaseUserCount(){
        //when
        user1.unfollow(user2);

        //then
        assertEquals(1, user1.followingCount());
        assertEquals(0, user1.followerCount());
        assertEquals(1, user2.followingCount());
        assertEquals(0, user2.followerCount());
    }
}
