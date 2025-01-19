package org.example.user.application.dto;

public class CreateUserRequestDto {

    private final String name;
    private final String ProfileImageUrl;

    public CreateUserRequestDto(String name, String profileImageUrl) {
        this.name = name;
        this.ProfileImageUrl = profileImageUrl;
    }

    public String getName() {
        return name;
    }
    public String getProfileImageUrl() {
        return ProfileImageUrl;
    }
}
