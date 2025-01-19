package org.example.post.repository.entity.comment;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Common.PositivelntegerCounter;
import org.example.Common.repository.entity.TimeBaseEntity;
import org.example.user.repository.entity.UserEntity;
import org.example.post.domain.Comment.Comment;
import org.example.post.domain.content.CommentContent;
import org.example.post.repository.entity.post.PostEntity;

@Entity
@Table(name = "community_comment")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CommentEntity extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "authorId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private UserEntity author;

    @ManyToOne
    @JoinColumn(name = "postId", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private PostEntity post;

    private String content;
    private Integer likeCounter;

   public CommentEntity(Comment comment) {
       this.id = comment.getId();
       this.author = new UserEntity(comment.getAuthor());
       this.post = new PostEntity(comment.getPost());
       this.content = comment.getContent();
       this.likeCounter = comment.getLikeCount();
   }

   public Comment toComment() {
       return Comment.builder()
               .id(id)
               .author(author.toUser())
               .post(post.toPost())
               .content(new CommentContent(content))
               .likeCount(new PositivelntegerCounter(likeCounter))
               .build();
   }
}
