package org.example.post.application;

import org.example.post.domain.comment.Comment;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommentServiceTest extends PostServiceTestTemplate {

    @Test
    void givenCreateCommentRequestDto_whenCreateComment_thenReturnComment(){
        //when
        Comment comment = commentService.createComment(createCommentRequestDto);

        //then
        String content = comment.getContent();
        assertEquals(commentContent, content);
    }

    @Test
    void givenCreateComment_whenUpdateComment_thenReturnUpdatedComment(){
        //given
        Comment comment = commentService.createComment(createCommentRequestDto);

        //when
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(user.getId(), "update-content");
        Comment updateComment = commentService.updateComment(comment.getId(), updateCommentRequestDto);

        //then
        assertEquals(comment.getId(), updateComment.getId());
        assertEquals(comment.getAuthor(), updateComment.getAuthor());
        assertEquals(comment.getContent(), updateComment.getContent());
    }

    @Test
    void givenCommentWhenUnlikeCommentThenReturnCommentWithoutLike() {
        // given
        Comment comment = commentService.createComment(createCommentRequestDto);

        // when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(otherUser.getId(), comment.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        // then
        assertEquals(0, comment.getLikeCount());
    }


    @Test
    void givenCommentWhenLikeSelfThenThrowException() {
        // given
        Comment comment = commentService.createComment(createCommentRequestDto);

        // when, then
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(user.getId(), comment.getId());
        assertThrows(IllegalArgumentException.class, () -> commentService.likeComment(likeRequestDto));
    }

}
