package org.example.post.application;

import org.example.post.domain.Comment.Comment;
import org.example.post.domain.application.dto.LIkeRequestDto;
import org.example.post.domain.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.application.dto.UpdatePostRequestDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest extends PostApplicationTestTemplate{

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
        UpdateCommentRequestDto updateCommentRequestDto = new UpdateCommentRequestDto(comment.getId(), user.getId(), "update-content");
        Comment updateComment = commentService.updateComment(updateCommentRequestDto);

        //then
        assertEquals(comment.getId(), updateComment.getId());
        assertEquals(comment.getAuthor(), updateComment.getAuthor());
        assertEquals(comment.getContent(), updateComment.getContent());
    }

    @Test
    void givenComment_whenLikeComment_thenReturnCommentWithLike(){
        //given
        Comment comment = commentService.createComment(createCommentRequestDto);

        //when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUnlikeComment_thenReturnCommentWithUnlike(){
        //given
        Comment comment = commentService.createComment(createCommentRequestDto);

        //when
        LIkeRequestDto likeRequestDto = new LIkeRequestDto(comment.getId(), otherUser.getId());
        commentService.likeComment(likeRequestDto);
        commentService.unlikeComment(likeRequestDto);

        //then
        assertEquals(0, comment.getLikeCount());
    }

}
