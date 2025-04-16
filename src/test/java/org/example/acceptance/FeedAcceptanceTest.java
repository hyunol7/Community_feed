package org.example.acceptance;

import org.example.acceptance.utils.AcceptanceTestTemplate;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.domain.content.PostPublicationState;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.example.acceptance.steps.FeedAcceptanceSteps.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class FeedAcceptanceTest extends AcceptanceTestTemplate {

    private String token;


    @BeforeEach
    void setUp(){
        super.init();
        this.token = login("user1@test.com");
    }

    @Test
    void givenUserHasFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed(){
        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = reqCreatePost(dto);

        //when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(token);

        //then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

    @Test
    void givenUserHasFollowerAndCreatePostWhenGetPostThenReturnPostWithInvalidToken() {
        // when, 팔로워의 피드 요청
        Integer resultCode = requestFeedCode("invalid token");

        // then
        assertEquals(400, resultCode);
    }


}



