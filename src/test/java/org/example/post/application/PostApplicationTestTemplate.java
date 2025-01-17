package org.example.post.application;

import org.example.User.application.UserService;
import org.example.User.application.dto.CreateUserRequestDto;
import org.example.User.domain.User;
import org.example.fake.FakeObjectFactory;
import org.example.post.domain.Post;
import org.example.post.domain.application.CommentService;
import org.example.post.domain.application.PostService;
import org.example.post.domain.application.dto.CreateCommentRequestDto;
import org.example.post.domain.application.dto.CreatePostRequestDto;
import org.example.post.domain.content.PostPublicationState;

class PostApplicationTestTemplate {


    final UserService userService = FakeObjectFactory.getUserService();
    final PostService postService = FakeObjectFactory.getPostService();
    final CommentService commentService = FakeObjectFactory.getCommentService();

    final User user = userService.createUser(new CreateUserRequestDto("user1", null));
    final User otherUser = userService.createUser(new CreateUserRequestDto("user1", null));

    CreatePostRequestDto postRequestDto = new CreatePostRequestDto(user.getId(), "this is test content", PostPublicationState.PUBLIC);
    final Post post = postService.createPost(postRequestDto);

    final String commentContent = "this is test content";
    CreateCommentRequestDto createCommentRequestDto = new CreateCommentRequestDto(post.getId(), user.getId(), "this is test content");


}
