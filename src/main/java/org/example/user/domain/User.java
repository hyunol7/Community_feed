package org.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.Common.PositivelntegerCounter;

import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor
public class User {

    private Long id;

    private UserInfo userInfo;
    private PositivelntegerCounter followingCount;
    private PositivelntegerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followerCount = new PositivelntegerCounter();
        this.followingCount = new PositivelntegerCounter();
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
        followingCount.increase();
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

