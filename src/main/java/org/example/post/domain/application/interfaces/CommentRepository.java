package org.example.post.domain.application.interfaces;

import org.example.post.domain.Comment.Comment;

import java.util.Optional;

public interface CommentRepository {

    Comment save(Comment comment);
    Optional<Comment> findById(Long id);
}
