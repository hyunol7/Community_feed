package org.example.post.domain.Comment;

import org.example.User.domain.User;
import org.example.User.domain.UserInfo;
import org.example.post.domain.Post;
import org.example.post.domain.content.CommentContent;
import org.example.post.domain.content.PostContent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommentTest {

    private final UserInfo info = new UserInfo("name", "url");
    private final User user = new User(1L, info);
    private final User otherUser = new User(2L, info);

    private final Post post = new Post(1L, user, new PostContent("content"));
    private final Comment comment = new Comment(1L, user, new CommentContent("content"), post);

    @Test
    void givenCommentCreated_whenLike_thenLikeCountShouldBe1() {
        //when
        comment.like(otherUser);

        //then
        assertEquals(1, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenLikeByOtherUser_thenThrowException() {
        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.like(user));
    }

    @Test
    void givenCommentCreatedAndLike_whenUnlike_thenLikeCountShouldBe0() {
        //given
        comment.like(otherUser);

        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenCommentCreated_whenUnlike_thenLikeCountShouldBe1() {
        //when
        comment.unlike();

        //then
        assertEquals(0, comment.getLikeCount());
    }

    @Test
    void givenComment_whenUpdateContent_thenShouldUpdate() {
        //given
        String newCommentContent = "new Content";

        //when
        comment.updateComment(user, newCommentContent);

        //then
        assertEquals(newCommentContent, comment.getContent());
    }

    @Test
    void givenComment_whenUpdateContentOver100_thenThrowException() {
        //given
        String newCommentContent = "a".repeat(101);

        //when, then
        assertThrows(IllegalArgumentException.class, () -> comment.updateComment(user, newCommentContent));
    }


}
