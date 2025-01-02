package org.example.User.application;

import org.example.User.application.Interface.UserRepository;
import org.example.User.application.dto.CreateUserRequestDto;
import org.example.User.domain.User;
import org.example.User.domain.UserInfo;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(CreateUserRequestDto dto) {
        UserInfo info = new UserInfo(dto.getName(), dto.getProfileImageUrl());
        User user = new User(null, info);
        return userRepository.save(user);
    }

    public User getUser(Long id){
        return userRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }
}
