package org.example.post.application.interfaces;

import org.example.post.domain.Post;

import java.util.Optional;

public interface PostRepository {

    Post save(Post post);

    Post findById(Long id);
}
