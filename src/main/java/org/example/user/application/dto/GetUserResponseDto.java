package org.example.user.application.dto;

import org.example.user.domain.User;

public record GetUserResponseDto(Long id, String name, String profileImage ,Integer followingCount, Integer followersCount) {

    public GetUserResponseDto(User user) {
        this(user.getId(), user.name(), user.getProfileImage(), user.followingCount(), user.followerCount());
    }
}


