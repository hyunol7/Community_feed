package org.example.post.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.post.application.interfaces.PostRepository;
import org.example.post.domain.Post;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.entity.post.UserPostQueueEntity;
import org.example.post.repository.jpa.JpaPostRepository;
import org.example.post.repository.post_queue.UserPostQueueCommentRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository jpaPostRepository;
    private final UserPostQueueCommentRepository userPostQueueCommentRepository;

    @Override
    @Transactional
    public Post save(Post post) {
        PostEntity postEntity = new PostEntity(post);

        postEntity = jpaPostRepository.save(postEntity);
        userPostQueueCommentRepository.publishPost(postEntity);
        return postEntity.toPost();
    }

    @Override
    public Post findById(Long id) {
        PostEntity postEntity = jpaPostRepository.findById(id).orElseThrow();
        return postEntity.toPost();
    }
}
