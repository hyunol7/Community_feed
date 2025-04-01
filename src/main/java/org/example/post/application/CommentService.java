package org.example.post.application;

import jakarta.transaction.Transactional;
import org.example.user.application.UserService;
import org.example.user.domain.User;
import org.example.post.domain.comment.Comment;
import org.example.post.domain.Post;
import org.example.post.application.dto.CreateCommentRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.dto.UpdateCommentRequestDto;
import org.example.post.application.interfaces.CommentRepository;
import org.example.post.application.interfaces.LIkeRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
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
        return commentRepository.findById(id);
    }
    public Comment createComment(CreateCommentRequestDto dto) {
        Post post = postService.getPost(dto.postId());
        User author = userService.getUser(dto.userId());
        Comment comment = new Comment(null, post, author, dto.content());
        return commentRepository.save(comment);
    }


    public Comment updateComment(Long commentId, UpdateCommentRequestDto dto){
        Comment comment = getComment(commentId);
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

