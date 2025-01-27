package org.example.post.ui;

import lombok.RequiredArgsConstructor;
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

        @GetMapping("/{userId}")
        public Response<List<GetPostContentResponseDto>> getPostFed(@PathVariable(name = "userId") Long userId, Long lastPostId ) {
            List<GetPostContentResponseDto> result = userPostQueueQueryRepository.getPostList(userId, lastPostId);
            return Response.ok(result);
        }
}
