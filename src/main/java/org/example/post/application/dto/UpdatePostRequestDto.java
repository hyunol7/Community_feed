package org.example.post.application.dto;

import org.example.post.domain.Post;
import org.example.post.domain.content.PostPublicationState;

public record UpdatePostRequestDto(Long userId, String content, PostPublicationState state) {


}
