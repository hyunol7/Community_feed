package org.example.post.domain;

import org.example.Common.PostisivelntengerCounter;
import org.example.User.domain.User;
import org.example.post.domain.content.PostContent;
import org.example.post.domain.content.PostPublicationState;

public class Post {

    private final User author;
    private final Long id;
    private final PostContent content;
    private final PostisivelntengerCounter likeCount;
    private PostPublicationState state;


    public Post(Long id, User author, PostContent content) {
        if(author == null) {
            throw new IllegalArgumentException("athoUser cannot be null");
        }



        this.id = id;
        this.author =  author;
        this.content = content;
        this.likeCount = new PostisivelntengerCounter();
        this.state = PostPublicationState.PUBLIC;
    }

    public void like(User user){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("athoUser cannot be null");
        }
        likeCount.increase();
    }

        public void unlike(User user){
            this.likeCount.descrease();
        }

        public void updatePost(User user, String content, PostPublicationState state){
        if(this.author.equals(user)){
            throw new IllegalArgumentException("athoUser cannot be null");
        }
        this.state = state;
        this.content.updateContent(content);
        }
    }



