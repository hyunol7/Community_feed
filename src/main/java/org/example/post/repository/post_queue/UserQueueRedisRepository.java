package org.example.post.repository.post_queue;

import org.example.post.repository.entity.post.PostEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserQueueRedisRepository {

    void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList);
    void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId);
    void deleteDeleteFeed(Long userId, Long authorId);
}
