package org.example.User.domain;

import lombok.Getter;

@Getter
public class UserInfo {

    private String name;
    private String ProfileImageUrl;

    public UserInfo(String name, String profileImageUrl) {

        if(name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name and profileImageUrl cannot be null");
        }
        this.name = name;
        this.ProfileImageUrl = profileImageUrl;
    }



}
