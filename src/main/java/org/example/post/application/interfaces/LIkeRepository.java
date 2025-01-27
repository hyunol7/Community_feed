package org.example.post.application.interfaces;

import org.example.user.domain.User;
import org.example.post.domain.comment.Comment;
import org.example.post.domain.Post;

public interface LIkeRepository {

    boolean checkLike(Post post, User user);
    void like(Post post, User user);
    void unlike(Post post, User user);
    boolean checkLike(Comment comment, User user);
    void like(Comment comment, User user);
    void unlike(Comment comment, User user);

}
