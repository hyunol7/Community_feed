package org.example.user.repository.jpa;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.user.application.dto.GetUserListResponseDto;
import org.example.user.repository.entity.QUserEntity;
import org.example.user.repository.entity.QUserRelationEntity;
import org.springframework.expression.spel.ast.Projection;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaUserListPagingQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity user = QUserEntity.userEntity;
    private static final QUserRelationEntity relation = QUserRelationEntity.userRelationEntity;

    public List<GetUserListResponseDto> getFollowerList(Long userId, Long lastFollowerId) {
        return queryFactory
                .select(
                        Projections.fields(
                            GetUserListResponseDto.class
                        )
                )
                .from(relation)
                .join(user).on(relation.followingUserId.eq(user.id))
                .where(
                        relation.followerUserId.eq(userId),
                        hasLastData(lastFollowerId)
                )
                .orderBy(user.id.desc())
                .limit(28)
                .fetch();
    }

    private BooleanExpression hasLastData(Long lastId) {
        if(lastId == null) {
            return null;
        }

        return user.id.lt(lastId);
    }

}
