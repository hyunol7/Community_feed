package org.example.User.domain;

public class UserInfo {

    private String name;
    private String ProfileImageUrl;

    public UserInfo(String name, String profileImageUrl) {

        if(name == null || profileImageUrl == null) {
            throw new IllegalArgumentException("name and profileImageUrl cannot be null");
        }
        this.name = name;
        this.ProfileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }

}
