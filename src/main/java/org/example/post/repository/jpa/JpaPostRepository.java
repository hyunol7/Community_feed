package org.example.post.repository.jpa;

import org.example.post.repository.entity.post.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostEntity, Long> {

    @Query("SELECT p.id FROM PostEntity p WHERE p.author.id = :authorId")
    List<PostEntity> findAllIdsByAuthorId(Long authorId);


    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.content = :#{#postEntity.getContent()}, " +
            "p.state = :#{#postEntity.getState()}, " +
            "p.updateDate = now() " +
            "WHERE p.id = :#{#postEntity.id}")
    void updatePostEntity(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.likeCount = :#{#postEntity.likeCount}, " +
            "p.updateDate = now() " +
            "WHERE p.id = :#{#postEntity.getId()}")
    void updateLikeCount(PostEntity postEntity);

    @Modifying
    @Query(value = "UPDATE PostEntity p " +
            "SET p.commentCount = :commentCount , " +
            "p.updateDate = now() " +
            "WHERE p.id = :id")
    void increaseCommentCount(@Param("id") Long Id);
}


