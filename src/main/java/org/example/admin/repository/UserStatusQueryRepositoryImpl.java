package org.example.admin.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.example.Common.TimerCalculator;
import org.example.admin.ui.dto.GetDailyRegisterUserResponseDto;
import org.example.admin.ui.query.UserStatusQueryRepository;

import org.example.user.repository.entity.QUserEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;

@Repository
@RequiredArgsConstructor
public class UserStatusQueryRepositoryImpl implements UserStatusQueryRepository {

    private final JPAQueryFactory queryFactory;
    private static final QUserEntity userEntity = QUserEntity.userEntity;

    @Override
    public List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStarts(int beforeDays) {
        return queryFactory
                .select(
                        Projections.fields(
                                GetDailyRegisterUserResponseDto.class,
                                userEntity.regDate.as("date"),
                                userEntity.count().as("count")
                        )
                )
                .from(userEntity)
                .where(userEntity.regDate.after(TimerCalculator.getCurrentDate(beforeDays)))
                .groupBy(userEntity.regDate)
                .orderBy(userEntity.regDate.asc())
                .fetch();
    }
}
