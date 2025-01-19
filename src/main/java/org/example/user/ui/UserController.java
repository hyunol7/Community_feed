package org.example.user.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.user.application.UserService;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.domain.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public Response<Long> createUser(@RequestBody CreateUserRequestDto requestDto) {
        User user = userService.createUser(requestDto);
        return Response.ok(user.getId());
    }
}
