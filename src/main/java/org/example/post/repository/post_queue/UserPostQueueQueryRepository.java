package org.example.post.repository.post_queue;

import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserPostQueueQueryRepository {
    List<GetPostContentResponseDto> getPostList(Long userId, Long postId);
}
