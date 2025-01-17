package org.example.post.domain.application.dto;

public record UpdateCommentRequestDto(Long commentId, Long userId, String content) {
}
