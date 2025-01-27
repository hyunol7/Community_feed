package org.example.user.ui;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.user.application.UserRelationService;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/relation")
@RequiredArgsConstructor
public class UserRelationController {

    private final UserRelationService userRelationService;

    @PostMapping("/follow")
    private Response<Void> followUser(@RequestBody @Valid FollowUserRequestDto dto){
        userRelationService.follow(dto);
        return Response.ok(null);
    }

    @PostMapping("/unfollow")
    private Response<Void> unfollowUser(@RequestBody @Valid FollowUserRequestDto dto){
        userRelationService.unfollow(dto);
        return Response.ok(null);
    }
}
