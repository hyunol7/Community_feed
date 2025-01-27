package org.example.post.repository.jpa;

import org.example.post.repository.entity.comment.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface JpaCommentRepository extends JpaRepository<CommentEntity, Long> {

    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.content = :content , " +
            "c.updateDate = now() " +
            "WHERE c.id = :id ")
    void updateCommentEntity(@Param("id") Long id, @Param("content") String content);

    @Modifying
    @Query(value = "UPDATE CommentEntity c " +
            "SET c.likeCount = :likeCount, " +
            "c.updateDate = now() " +
            "WHERE c.id = :id")
    void updateLikeCount(@Param("id") Long id, @Param("likeCount") Integer likeCount);
}
