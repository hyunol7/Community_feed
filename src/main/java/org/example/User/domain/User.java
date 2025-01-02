package org.example.User.domain;

import org.example.Common.PostisivelntengerCounter;

import java.util.Objects;

public class User {

    private final Long id;
    private final UserInfo userInfo;
    private final PostisivelntengerCounter followingCount;
    private final PostisivelntengerCounter followerCount;

    public User(Long id, UserInfo userInfo) {
        if (userInfo == null) {
            throw new IllegalArgumentException("userInfo cannot be null");
        }

        this.id = id;
        this.userInfo = userInfo;
        this.followerCount = new PostisivelntengerCounter();
        this.followingCount = new PostisivelntengerCounter();
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
        followerCount.descrease();
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

    public Long getId() {
        return id;
    }

    public int followerCount() {
        return followerCount.getCount();
    }

    public int followingCount(){
        return followingCount.getCount();
    }

    public UserInfo getInfo() {
        return userInfo;
    }


}

