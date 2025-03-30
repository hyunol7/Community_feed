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

import static org.example.acceptance.steps.FeedAcceptanceSteps.reqCreatePost;
import static org.example.acceptance.steps.FeedAcceptanceSteps.requestFeed;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FeedAcceptanceTest extends AcceptanceTestTemplate {


    @BeforeEach
    void setUp(){
        super.init();
    }
    @Test
    void givenUserHashFollowerAndCreatePost_whenFollowerUserRequestFeed_thenFollowerCanGetPostFromFeed(){
        //given
        CreatePostRequestDto dto = new CreatePostRequestDto(2L, "user 1 can get this post", PostPublicationState.PUBLIC);
        Long createdPostId = reqCreatePost(dto);

        //when, 팔로워 피드를 요청
        List<GetPostContentResponseDto> result = requestFeed(1L);

        //then
        assertEquals(1, result.size());
        assertEquals(createdPostId, result.get(0).getId());
    }

}



