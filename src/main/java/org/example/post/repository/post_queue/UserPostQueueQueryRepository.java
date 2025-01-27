package org.example.post.repository.post_queue;

import org.example.post.ui.dto.GetPostContentResponseDto;

import java.util.List;

public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostList(Long userId, Long postId);
}
