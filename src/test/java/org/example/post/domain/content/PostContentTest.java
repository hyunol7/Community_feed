package org.example.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostContentTest {

    @Test
    void givenContentLengthIsOk_whenCreated_thenReturnTextContent(){
        //given
        String text = "this is a test";

        //when
        PostContent content = new PostContent(text);

        //then
        assertEquals(text, content.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenCreated_thenThrowError(){
        //given
        String content = "a".repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가,나,다,라,마"})
    void givenContentLengthIsOverAndKorean_whenCreated_thenThrowError(String koreanWord){
        //given
        String content = koreanWord.repeat(501);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @Test
    void givenContentLengthIsUser_whenCreated_thenThrowError(){
        //given
        String content = "a".repeat(4);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmpty_whenCreated_thenThrowError(String value){
        //when, then
        assertThrows(IllegalArgumentException.class, () -> new PostContent(value));
    }




}
