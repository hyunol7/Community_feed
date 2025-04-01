package org.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.Common.domain.PositiveIntegerCounter;

import java.util.Objects;


@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private UserInfo userInfo;
    private PositiveIntegerCounter followingCount;
    private PositiveIntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followerCount = new PositiveIntegerCounter();
        this.followingCount = new PositiveIntegerCounter();
    }

    public User(String name, String profileImageUrl) {
        this(null, new UserInfo(name, profileImageUrl));
    }

    public void follow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("You can't follow a user");
        }
        followingCount.increase();
        targetUser.increaseFollowIncrease();
    }

    public void unfollow(User targetUser) {
        if (targetUser.equals(this)) {
            throw new IllegalArgumentException("You can't unfollow a user");

        }
        followingCount.decrease();
        targetUser.decreaseFollowDecrease();
    }

    private void increaseFollowIncrease() {
        followerCount.increase();
    }

    private void decreaseFollowDecrease() {
        followerCount.decrease();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount(){
        return followingCount.getCount();
    }

    public String getProfileImage(){
        return userInfo.getProfileImageUrl();
    }

    public String name(){
        return userInfo.getName();
    }

}

