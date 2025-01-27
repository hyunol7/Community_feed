package org.example.user.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.post.repository.post_queue.UserPostQueueCommentRepository;
import org.example.post.repository.post_queue.UserPostQueueCommentRepositoryImpl;
import org.example.user.application.Interface.UserRelationRepository;
import org.example.user.domain.User;
import org.example.user.repository.entity.UserEntity;
import org.example.user.repository.entity.UserRelationEntity;
import org.example.user.repository.entity.UserRelationIdEntity;
import org.example.user.repository.jpa.JpaUserRelationRepository;
import org.example.user.repository.jpa.JpaUserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRelationRepositoryImpl implements UserRelationRepository {

    private final JpaUserRelationRepository jpaUserRelationRepository;
    private final JpaUserRepository jpauserRepository;
    private final UserPostQueueCommentRepository userPostQueueCommentRepository;
    private final UserPostQueueCommentRepositoryImpl commentRepositoryImpl;

    @Override
    public boolean isAlreadyFollow(User user, User targetUser){
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        return jpaUserRelationRepository.existsById(id);
    }

    @Override
    @Transactional
    public void save(User user, User targetUser) {
        UserRelationEntity entity = new UserRelationEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.save(entity);
        jpauserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commentRepositoryImpl.saveFollowPost(user.getId(), targetUser.getId());
    }

    @Override
    @Transactional
    public void delete(User user, User targetUser) {
        UserRelationIdEntity id = new UserRelationIdEntity(user.getId(), targetUser.getId());
        jpaUserRelationRepository.deleteById(id);
        jpauserRepository.saveAll(List.of(new UserEntity(user), new UserEntity(targetUser)));
        commentRepositoryImpl.saveFollowPost(user.getId(), targetUser.getId());
    }


}
