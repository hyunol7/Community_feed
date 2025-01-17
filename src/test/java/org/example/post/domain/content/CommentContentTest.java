package org.example.post.domain.content;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CommentContentTest {

    @Test
    void givenContentLengthIsOk_WhenCreateCommentContent_thenReturnTextContext() {
        //given
        String contentText = "test content";

        //when
        CommentContent content = new CommentContent(contentText);

        //then
        assertEquals(contentText, content.getContentText());
    }

    @Test
    void givenContentLengthIsOver_whenCreatedCommentContent_thenThrowError(){
        //given
        String content = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @ValueSource(strings = {"가","나","다"})
    void givenContentLengthIsOverAndKorean_whenCreatedCommentContent_thenThrowError(String koreanContent){
        //given
        String content = koreanContent.repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }

    @ParameterizedTest
    @NullAndEmptySource
    void givenContentLengthIsEmptyANdNull_whenCreatedCommentContent_thenThrowError(String content){
        assertThrows(IllegalArgumentException.class, () -> new CommentContent(content));
    }


}
