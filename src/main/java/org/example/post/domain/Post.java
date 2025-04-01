package org.example.post.domain;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.example.Common.domain.PositiveIntegerCounter;
import org.example.user.domain.User;
import org.example.post.domain.content.Content;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;

@Getter
@AllArgsConstructor
public class Post {

    private final User author;
    private final Long id;
    private final Content content;
    private final PositiveIntegerCounter likeCount;
    private PostPublicationState state;



    @Builder
    public Post(Long id, User author, Content content, PostPublicationState state, PositiveIntegerCounter positiveIntegerCounter) {
        if (author == null) {
            throw new IllegalArgumentException("author should not be null");
        }
        if (content == null) {
            throw new IllegalArgumentException("content should not be null or empty");
        }

        this.id = id;
        this.author =  author;
        this.content = content;
        this.likeCount = new PositiveIntegerCounter();
        this.state = state;
    }

    public Post(Long id, User author, Content content) {
        this(id, author, content, PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public Post(Long id, User author, String content) {
        this(id, author, new PostContent(content), PostPublicationState.PUBLIC, new PositiveIntegerCounter());
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("author cannot like own null");
        }
        likeCount.increase();
    }

    public void unlike(){
            this.likeCount.decrease();
        }

    public void updatePost(User user, String updateContent, PostPublicationState state){
        if(!this.author.equals(user)){
            throw new IllegalArgumentException();
        }
        this.state = state;
        this.content.updateContent(updateContent);
        }

    public int getLikeCount(){
            return likeCount.getCount();
        }

    public String getContent(){
            return content.getContentText();
        }

    public Content getContentObject(){
        return content;
    }







    }






