package org.example.fake;

import org.example.user.Repository.FakeUserRelationRepository;
import org.example.user.Repository.FakeUsreRepository;
import org.example.user.application.Interface.UserRelationRepository;
import org.example.user.application.Interface.UserRepository;
import org.example.user.application.UserRelationService;
import org.example.user.application.UserService;
import org.example.post.application.CommentService;
import org.example.post.application.PostService;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.application.interfaces.LIkeRepository;
import org.example.post.application.interfaces.PostRepository;
import org.example.post.domain.repository.FakeCommentRepository;
import org.example.post.domain.repository.FakeLikeRepositoyry;
import org.example.post.domain.repository.FakePostRepository;

public class FakeObjectFactory {

    private static final UserRepository fakeUserRepository = new FakeUsreRepository();
    private static final UserRelationRepository fakeUserRelationRepository = new FakeUserRelationRepository();
    private static final PostRepository fakePostRepository = new FakePostRepository();
    private static final CommentRepository fakeCommentRepository = new FakeCommentRepository();
    private static final LIkeRepository fakeLikeRepository = new FakeLikeRepositoyry();

    private static final UserService userService = new UserService(fakeUserRepository);
    private static final UserRelationService userRelationService = new UserRelationService(userService ,fakeUserRelationRepository);
    private static final PostService postService = new PostService(fakePostRepository, userService, fakeLikeRepository);
    private static final CommentService commentService = new CommentService(userService, postService, fakeCommentRepository, fakeLikeRepository);

    private FakeObjectFactory() {}

    public static UserService getUserService() {
        return userService;
    }

    public static UserRelationService getUserRelationService() {
        return userRelationService;
    }

    public static PostService getPostService() {
        return postService;
    }

    public static CommentService getCommentService() {
        return commentService;
    }


}
