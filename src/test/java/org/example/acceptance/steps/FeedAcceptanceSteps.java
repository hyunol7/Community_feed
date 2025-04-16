package org.example.acceptance.steps;

import io.restassured.RestAssured;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.ui.dto.GetPostContentResponseDto;
import org.springframework.http.MediaType;

import java.util.List;

public class FeedAcceptanceSteps {

    public static Long reqCreatePost(CreatePostRequestDto dto) {
        return RestAssured
                .given().log().all()
                .body(dto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/post")
                .then().log().all()
                .extract()
                .jsonPath()
                .getObject("value", Long.class);
    }
    public static List<GetPostContentResponseDto> requestFeed(String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .getList("value", GetPostContentResponseDto.class);
    }

    public static Integer requestFeedCode(String token) {
        return RestAssured
                .given().log().all()
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .header("Authorization", "Bearer " + token)
                .get("/feed")
                .then().log().all()
                .extract()
                .jsonPath()
                .get("code");
    }
}
