package org.example.post.domain.Comment;

import lombok.AllArgsConstructor;
import lombok.Builder;

import org.example.Common.domain.PositiveIntegerCounter;
import org.example.user.domain.User;
import org.example.post.domain.Post;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.Content;

@Builder
@AllArgsConstructor
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private final Content content;
    private final PositiveIntegerCounter likeCount;

    public static Comment createComment(Post post, User author, String content){
        return new Comment(null, author, new CommentContent(content), post);
    }

    public Comment(Long id, User author, Content content, Post post) {
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
        this.likeCount = new PositiveIntegerCounter();

    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("You cannot like a user");
        }
        likeCount.increase();
    }
    public void unlike(){
        this.likeCount.decrease();
    }

    public void updateComment(User user, String updateContent){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException("You cannot update a user");
        }
        this.content.updateContent(updateContent);
    }

    public int getLikeCount(){
        return likeCount.getCount();
    }

    public String getContent(){
        return content.getContentText();
    }

    public Long getId(){
        return id;
    }

    public Post getPost(){
        return post;
    }

    public User getAuthor(){
        return author;
    }

    public Content getContentContent(){
        return content;
    }
}
