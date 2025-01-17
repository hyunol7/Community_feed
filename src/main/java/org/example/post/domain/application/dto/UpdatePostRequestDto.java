package org.example.post.domain.application.dto;

import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long postId, Long userId, String content, PostPublicationState state) {


}
