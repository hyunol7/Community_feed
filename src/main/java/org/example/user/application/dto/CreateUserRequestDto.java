package org.example.user.application.dto;

import lombok.Getter;

@Getter
public class CreateUserRequestDto {

    private final String name;
    private final String ProfileImageUrl;

    public CreateUserRequestDto(String name, String profileImageUrl) {
        this.name = name;
        this.ProfileImageUrl = profileImageUrl;
    }

}
