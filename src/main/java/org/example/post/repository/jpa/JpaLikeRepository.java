package org.example.post.repository.jpa;

import org.example.post.repository.entity.like.LIkeIdEntity;
import org.example.post.repository.entity.like.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaLikeRepository extends JpaRepository<LikeEntity, LIkeIdEntity> {
}
