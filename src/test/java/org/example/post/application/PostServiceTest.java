package org.example.post.application;

import org.example.post.domain.Post;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostApplicationTestTemplate{

    CreatePostRequestDto dto = new CreatePostRequestDto(user.getId(), "test-content", PostPublicationState.PUBLIC);

    @Test
    void givenPostRequestDto_whenCreate_thenReturnPost(){
        //when
        Post savedPost = postService.createPost(dto);

        //then
        Post post = postService.getPost(savedPost.getId());
        assertEquals(savedPost, post);
    }

    @Test
    void givenCreatePost_whenUpdate_thenReturnUpdatePost(){
        //given
        Post savedPost = postService.createPost(dto);

        //when
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(savedPost.getId(),user.getId(),"update-content", PostPublicationState.PRIVATE);
        Post updatePost = postService.updatePost(savedPost.getId(), dto);

        //then
        assertEquals(savedPost.getId(), updatePost.getId());
        assertEquals(savedPost.getAuthor(), updatePost.getAuthor());
        assertEquals(savedPost.getContent(), updatePost.getContent());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWithLike(){
        //given
        Post savedPost = postService.createPost(dto);

        //when
        LIkeRequestDto lIkeRequestDto = new LIkeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(lIkeRequestDto);

        //then
        assertEquals(1, savedPost.getLikeCount());

    }

    @Test
    void givenCreatePostLiked_whenUnLiked_thenReturnPostWithLike(){
        //given
        Post savedPost = postService.createPost(dto);
        LIkeRequestDto lIkeRequestDto = new LIkeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(lIkeRequestDto);

        //when
        postService.unLikePost(lIkeRequestDto);

        //then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatePost_whenLiked_thenReturnPostWIthLike() {
        //given
        Post savedPost = postService.createPost(dto);

        //when
        LIkeRequestDto lIkeRequestDto = new LIkeRequestDto(savedPost.getId(), otherUser.getId());
        postService.likePost(lIkeRequestDto);

        //then
        assertEquals(0, savedPost.getLikeCount());
    }







}
