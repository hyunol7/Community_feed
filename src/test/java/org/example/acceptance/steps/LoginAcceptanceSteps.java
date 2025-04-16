package org.example.acceptance.steps;

import io.restassured.RestAssured;
import org.example.auth.application.dto.LoginRequestDto;
import org.example.auth.application.dto.UserAccessTokenResponseDto;
import org.springframework.http.MediaType;

public class LoginAcceptanceSteps {

    public static String requestLoginGetTest(LoginRequestDto loginRequestDto) {
        UserAccessTokenResponseDto res = RestAssured
                .given()
                .body(loginRequestDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("login")
                .then()
                .extract()
                .jsonPath()
                .getObject("value", UserAccessTokenResponseDto.class);
        return res.accessToken();
    }

    public static Integer requestLoginGetResponseCode(LoginRequestDto loginRequestDto) {
        return RestAssured
                .given()
                .body(loginRequestDto)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("login")
                .then()
                .extract()
                .jsonPath().getObject("code", Integer.class);
    }


}
