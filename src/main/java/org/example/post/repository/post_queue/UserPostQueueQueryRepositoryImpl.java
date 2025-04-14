package org.example.post.repository.post_queue;

import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Profile("!test")
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository {

    private final UserQueueRedisRepositoryImpl queueRepository;

    public UserPostQueueQueryRepositoryImpl(UserQueueRedisRepositoryImpl queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long postId) {
       List<PostEntity> postEntities = queueRepository.getPostByUserId(userId);

       List<GetPostContentResponseDto> result = new ArrayList<>();
       for(PostEntity postEntity : postEntities) {
           GetPostContentResponseDto dto = GetPostContentResponseDto.builder()
                   .id(postEntity.getId())
                   .content(postEntity.getContent())
                   .commentCount(postEntity.getCommentCount())
                   .build();
           result.add(dto);
       }
        return result;
    }
}