package org.example.auth.application;

import lombok.RequiredArgsConstructor;
import org.example.auth.application.dto.CreateUserAuthRequestDto;
import org.example.auth.application.dto.LoginRequestDto;
import org.example.auth.application.dto.UserAccessTokenResponseDto;
import org.example.auth.application.interfaces.EmailVerificationRepository;
import org.example.auth.application.interfaces.UserAuthRepository;
import org.example.auth.domain.Email;
import org.example.auth.domain.TokenProvider;
import org.example.auth.domain.UserAuth;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserAuthRepository userAuthRepository;
    private final EmailVerificationRepository emailVerificationRepository;
    private final TokenProvider tokenProvider;

    public Long registerUser(CreateUserAuthRequestDto dto) {
        Email email = Email.createEmail(dto.email());

        if(!emailVerificationRepository.isEmailVerified(email)){
            throw new IllegalArgumentException("Email verification failed");
        }

        UserAuth userAuth = new UserAuth(dto.email(), dto.password(), dto.role());
        User user = new User(dto.name(), dto.profileImgUrl());
        userAuth = userAuthRepository.registerUser(userAuth, user);
        return userAuth.getUserId();
    }

    public UserAccessTokenResponseDto login(LoginRequestDto dto) {
        UserAuth userAuth = userAuthRepository.loginUser(dto.email(), dto.password());
        String token = tokenProvider.createToken(userAuth.getUserId(), userAuth.getUserRole());
        return  new UserAccessTokenResponseDto(token);
    }
}
