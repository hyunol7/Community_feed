package org.example.post.repository.entity.like;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.user.domain.User;
import org.example.post.domain.comment.Comment;
import org.example.post.domain.Post;

@Entity
@Table(name = "community_like")
@NoArgsConstructor
@Getter
public class LikeEntity{

    @EmbeddedId
    private LIkeIdEntity id;

    public LikeEntity(Post post, User likeUser) {
        this.id = new LIkeIdEntity(post.getId(), likeUser.getId(), LIkeTarget.POST.name());
    }

    public LikeEntity(Comment comment, User likeUser) {
        this.id = new LIkeIdEntity(comment.getId(), likeUser.getId(), LIkeTarget.COMMENT.name());
    }


}
