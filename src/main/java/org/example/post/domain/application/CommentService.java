package org.example.post.domain.application;

import org.example.User.application.UserService;
import org.example.User.domain.User;
import org.example.post.domain.Comment.Comment;
import org.example.post.domain.Post;
import org.example.post.domain.application.dto.CreateCommentRequestDto;
import org.example.post.domain.application.dto.LIkeRequestDto;
import org.example.post.domain.application.dto.UpdateCommentRequestDto;
import org.example.post.domain.application.interfaces.CommentRepository;
import org.example.post.domain.application.interfaces.LIkeRepository;

public class CommentService {

    private final UserService userService;
    private final PostService postService;
    private final CommentRepository commentRepository;
    private final LIkeRepository likeRepository;

    public CommentService(UserService userService, PostService postService, CommentRepository commentRepository, LIkeRepository likeRepository) {
        this.userService = userService;
        this.postService = postService;
        this.commentRepository = commentRepository;
        this.likeRepository = likeRepository;
    }


    public Comment getComment(Long id){
        return commentRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    public Comment createComment(CreateCommentRequestDto dto){
        Post post = postService.getPost(dto.postId());
        User user = userService.getUser(dto.userId());

        Comment comment = Comment.createComment(post, user, dto.content());
        return commentRepository.save(comment);
    }

    public Comment updateComment(UpdateCommentRequestDto dto){
        Comment comment = getComment(dto.commentId());
        User user = userService.getUser(dto.userId());

        comment.updateComment(user, dto.content());
        return commentRepository.save(comment);
    }


    public void likeComment(LIkeRequestDto dto){
        Comment comment = getComment(dto.id());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            return;
        }

        comment.like(user);
        likeRepository.like(comment, user);
    }

    public void unlikeComment(LIkeRequestDto dto){
        Comment comment = getComment(dto.id());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(comment, user)){
            return;
        }

        comment.unlike();
        likeRepository.unlike(comment, user);
    }




}

