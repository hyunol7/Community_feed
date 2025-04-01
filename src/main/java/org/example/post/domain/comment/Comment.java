package org.example.post.domain.comment;

import lombok.Builder;

import lombok.Getter;
import org.example.Common.domain.PositiveIntegerCounter;
import org.example.user.domain.User;
import org.example.post.domain.Post;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.Content;

@Getter
public class Comment {

    private final Long id;
    private final Post post;
    private final User author;
    private Content content;
    private final PositiveIntegerCounter likeCount;


    public Comment(Long id, Post post, User author, CommentContent content) {
        this(id, post, author, content, new PositiveIntegerCounter());
    }


    @Builder
    public Comment(long id, User author, Content content, Post post,PositiveIntegerCounter likeCount) {
        if (post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }

        this.id = id;
        this.post = post;
        this.author = author;
        this.content = content;
        this.likeCount = likeCount; // 기본값 초기화
    }

    public Comment(long id, Post post, User author, Content contentContent, int likeCount, Long id1, Post post1, User author1, PositiveIntegerCounter likeCount1) {
        this.id = id1;
        this.post = post1;
        this.author = author1;
        this.likeCount = likeCount1;
    }




    public static Comment createComment(Long id, Post post, User author, String content) {
        return new Comment(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
    }


    public Comment(Long id, Post post, User author, Content content, PositiveIntegerCounter likeCount) {
        if(post == null) {
            throw new IllegalArgumentException("Post cannot be null");
        }
        if(author == null){
            throw new IllegalArgumentException("author cannot be null");
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

    public Comment(Long id, Post post, User author, String content) {
        this(id, post, author, new CommentContent(content), new PositiveIntegerCounter());
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
