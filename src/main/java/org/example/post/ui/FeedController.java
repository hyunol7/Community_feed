package org.example.post.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.principal.AuthPrincipal;
import org.example.Common.principal.UserPrincipal;
import org.example.Common.ui.Response;
import org.example.post.repository.post_queue.UserPostQueueQueryRepository;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@RequiredArgsConstructor
public class FeedController {

        private final UserPostQueueQueryRepository userPostQueueQueryRepository;

        @GetMapping("")
        public Response<List<GetPostContentResponseDto>> getPostFed(@AuthPrincipal UserPrincipal userPrincipal, Long lastPostId ) {
            List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getPostList(userPrincipal.getUserId(), lastPostId);
            return Response.ok(result);
        }
}
