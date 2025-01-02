package org.example.post.domain.Comment;

import org.example.Common.PostisivelntengerCounter;
import org.example.User.domain.User;
import org.example.post.domain.Post;
import org.example.post.domain.content.Content;

public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PostisivelntengerCounter likeCount;


    public Comment(Long id, Post post, User author, Content content) {
        if(author == null) {
            throw new IllegalArgumentException("author cannot be null");
        }
        if(post == null){
            throw new IllegalArgumentException("Post cannot be null");
        }
        if(content == null){
            throw new IllegalArgumentException("Content cannot be null");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = new PostisivelntengerCounter();
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("You cannot like a user");
        }
        likeCount.increase();
    }
    public void unlike(User user){
        this.likeCount.descrease();
    }

    public void updateComment(User user, String updateContent){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("You cannot update a user");
        }
        this.content.updateContent(updateContent);
    }
}
