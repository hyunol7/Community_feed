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

    @Test
    void givenContentLengthIsOk_whenUpdated_thenReturnUpdateContent(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when
        String updateContent = "this is a update content";
        postContent.updateContent(updateContent);

        //then
        assertEquals(updateContent, postContent.contentText);
    }

    @Test
    void givenContentLengthIsOver_whenUpdated_thenThrowError(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when,then
        String value = "a".repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가,나,다,라,마"})
    void givenContentLengthIsOverAndKorean_whenUpdated_thenThrowError(String koreanWord){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when, then
        String value = koreanWord.repeat(501);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));

    }

    @Test
    void givenContentLengthIsUser_whenUpdated_thenThrowError(){
        //given
        String content = "this is a test content";
        PostContent postContent = new PostContent(content);

        //when, then
        String value = "a".repeat(4);
        assertThrows(IllegalArgumentException.class, () -> postContent.updateContent(value));
    }






}
