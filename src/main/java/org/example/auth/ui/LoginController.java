package org.example.auth.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.auth.application.AuthService;
import org.example.auth.application.dto.LoginRequestDto;
import org.example.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final AuthService authService;

    @PostMapping
    public Response<UserAccessTokenResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return Response.ok(authService.login(loginRequestDto));
    }
}
