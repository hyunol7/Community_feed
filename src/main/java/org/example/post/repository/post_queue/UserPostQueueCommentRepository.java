package org.example.post.repository.post_queue;

import org.example.post.repository.entity.post.PostEntity;

public interface UserPostQueueCommentRepository {
    void publishPost(PostEntity postEntity);
    void saveFollowPost(Long userId, Long targetId);
    void deleteUnFollowPost(Long userId, Long targetId);
}
