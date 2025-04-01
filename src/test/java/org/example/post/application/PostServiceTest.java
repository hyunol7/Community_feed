package org.example.post.application;

import org.example.post.domain.Post;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.content.Content;
import org.example.post.domain.content.PostPublicationState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PostServiceTest extends PostServiceTestTemplate {

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
        UpdatePostRequestDto updateDto = new UpdatePostRequestDto(user.getId(),"update-content", PostPublicationState.PRIVATE);
        Post updatePost = postService.updatePost(savedPost.getId(), updateDto);

        //then
        assertEquals(savedPost.getId(), updatePost.getId());
        assertEquals(savedPost.getAuthor(), updatePost.getAuthor());
        assertEquals(savedPost.getContent(), updatePost.getContent());
    }

    @Test
    void givenCreatedPostWhenLikedThenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(otherUser.getId(), savedPost.getId());
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenLikedTwiceThenReturnPostWithLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(otherUser.getId(), savedPost.getId());
        postService.likePost(likeRequestDto);
        postService.likePost(likeRequestDto);

        // then
        assertEquals(1, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenUnlikedThenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(otherUser.getId(), savedPost.getId());
        postService.likePost(likeRequestDto);
        postService.unLikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }

    @Test
    void givenCreatedPostWhenUnlikedTwiceThenReturnPostWithoutLike() {
        // given
        Post savedPost = postService.createPost(dto);

        // when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(otherUser.getId(), savedPost.getId());
        postService.likePost(likeRequestDto);
        postService.unLikePost(likeRequestDto);
        postService.unLikePost(likeRequestDto);

        // then
        assertEquals(0, savedPost.getLikeCount());
    }
}