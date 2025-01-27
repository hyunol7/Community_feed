package org.example.post.repository.post_queue;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.entity.like.QLikeEntity;
import org.example.post.repository.entity.post.PostEntity;
import org.example.post.repository.entity.post.QPostEntity;
import org.example.post.repository.entity.post.QUserPostQueueEntity;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.example.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserPostQueueQueryRepositoryImpl implements UserPostQueueQueryRepository{

    private final JPAQueryFactory jpaQueryFactory;
    private static final QUserPostQueueEntity qUserPostRelationEntity = QUserPostQueueEntity.userPostQueueEntity;
    private static final QPostEntity qPostEntity = QPostEntity.postEntity;
    private static final QUserEntity qUserEntity = QUserEntity.userEntity;
    private static final QLikeEntity qlikeEntity = QLikeEntity.likeEntity;

    public List<GetPostContentResponseDto> getPostContentResponseDtos(Long userId, Long lastContentID) {
        return jpaQueryFactory
                .select(
                        Projections.fields(
                                GetPostContentResponseDto.class,
                                qPostEntity.id.as("id"),
                                qPostEntity.content.as("content"),
                                qUserEntity.id.as("userId"),
                                qUserEntity.name.as("userName"),
                                qUserEntity.profileImage.as("profileImage"),
                                qPostEntity.createdDate.as("createDate"),
                                qPostEntity.updateDate.as("updateDate"),
                                qPostEntity.commentCount.as("commentCount"),
                                qPostEntity.likeCount.as("likeCount"),
                                //유저의 좋아요 not null로 체크하기
                                qlikeEntity.isNotNull().as("isLikeByMe")
                        )
                )
                .from(qUserPostRelationEntity)
                //유저의 대한 정보
                .join(qPostEntity).on(qUserPostRelationEntity.postId.eq(qPostEntity.id))
                .join(qUserEntity).on(qUserPostRelationEntity.authorId.eq(qUserEntity.id))
                        .leftJoin(qlikeEntity).on(hasLike(userId))
                        .where(
                                qUserPostRelationEntity.userId.eq(userId),
                                hasLastDate(lastContentID)
                        )
                .orderBy(qUserPostRelationEntity.postId.desc())
                .limit(20)
                .fetch();
    }

    private BooleanExpression hasLastDate(Long lastContentID) {
        if(lastContentID == null){
            return null;
        }
        return qPostEntity.id.lt(lastContentID);
    }

    private BooleanExpression hasLike(Long userId) {
        if(userId == null) {
            return null;
        }
        //내가 좋아요를 눌렀는지 확인
        return qPostEntity.id
                .eq(qlikeEntity.id.targetId)
                .and(qlikeEntity.id.targetType.eq("POST"))
                .and(qlikeEntity.id.userId.eq(userId));
    }

    @Override
    public List<GetPostContentResponseDto> getPostList(Long userId, Long postId) {
        return List.of();
    }
}
