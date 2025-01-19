package org.example.post.domain.repository;

import org.example.user.domain.User;
import org.example.post.domain.Comment.Comment;
import org.example.post.domain.Post;
import org.example.post.application.interfaces.LIkeRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static javax.swing.UIManager.put;

public class FakeLikeRepositoyry implements LIkeRepository {


    private final Map<Post, Set<User>> postLikes = new HashMap<>();
    private final Map<Comment, Set<User>> commentLikes = new HashMap<>();

    @Override
    public boolean checkLike(Post post, User user) {
        if(postLikes.get(post) == null){
            return false;
        }
        return postLikes.get(post).contains(user);
    }

    @Override
    public void like(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null){
            users = new HashSet<>();
            {
                users.add(user);
            }
        }
        postLikes.put(post, users);
    }

    @Override
    public void unlike(Post post, User user) {
        Set<User> users = postLikes.get(post);
        if(users == null){
            return;
        }
        users.remove(user);
        postLikes.put(post, users);
    }

    @Override
    public boolean checkLike(Comment comment, User user) {
        if(commentLikes.get(comment) == null){
            return false;
        }
        return commentLikes.get(comment).contains(user);
    }

    @Override
    public void like(Comment comment, User user) {
        Set<User> users = commentLikes.get(comment);
        if(users == null){
            users = new HashSet<>();
            {
                users.add(user);
            }
        }put(comment, users);
    }

    @Override
    public void unlike(Comment comment, User user) {
        Set<User> users = commentLikes.get(comment);
        if(users == null){
            return;
        }
        users.remove(user);
        commentLikes.put(comment, users);
    }
}
