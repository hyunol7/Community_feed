package org.example.post.domain.repository;

import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.post_queue.UserQueueRedisRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

@Primary
@Repository
@Profile("test")
public class FakeUserQueueRedisRepository implements UserQueueRedisRepository {

    private final Map<Long, Set<PostEntity>> queue = new HashMap<>();

    @Override
    public void publishPostToFollowingUserList(PostEntity postEntity, List<Long> userIdList) {
        for(Long userId : userIdList) {
            if(queue.containsKey(userId)){
                queue.get(userId).add(postEntity);
            }else{
                queue.put(userId, new HashSet<>(List.of(postEntity)));
            }
        }
    }

    @Override
    public void publishPostListToFollowerUser(List<PostEntity> postEntityList, Long userId) {
        if(queue.containsKey(userId)){
            queue.get(userId).addAll(postEntityList);
        }else{
            queue.put(userId, new HashSet<>(postEntityList));
        }
    }

    @Override
    public void deleteDeleteFeed(Long userId, Long authorId) {
        if(queue.containsKey(userId)){
            queue.get(userId).removeIf(postEntity -> postEntity.getId().equals(authorId));
        }
    }

    public List<PostEntity> getPostListByUserId(Long userId){
        return List.copyOf(queue.get(userId));
    }
}
