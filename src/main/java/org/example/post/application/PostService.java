package org.example.post.application;

import jakarta.transaction.Transactional;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.user.application.UserService;
import org.example.user.domain.User;
import org.example.post.domain.Post;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.interfaces.LIkeRepository;
import org.example.post.application.interfaces.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    private final UserService userService;

    private final PostRepository postRepository;

    private final LIkeRepository likeRepository;

    public PostService(PostRepository postRepository, UserService userService, LIkeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.likeRepository = likeRepository;
    }

    public Post getPost(Long id){
        return postRepository.findById(id);
    }

public Post createPost(CreatePostRequestDto dto){
    User author = userService.getUser(dto.userId());
    Post post = new Post(null, author, dto.content());
    return postRepository.save(post);
}

    public Post updatePost(Long postId, UpdatePostRequestDto dto){
        Post post = getPost(postId);
        User user = userService.getUser(dto.userId());

        post.updatePost(user, dto.content(), dto.state());
        return postRepository.save(post);
    }

    public void likePost(LIkeRequestDto dto){
        Post post = getPost(dto.id());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            return;
        }

        post.like(user);
        likeRepository.like(post, user);
    }

    public void unLikePost(LIkeRequestDto dto){
        Post post = getPost(dto.id());
        User user = userService.getUser(dto.userId());

        if(likeRepository.checkLike(post, user)){
            post.unlike();
            likeRepository.unlike(post, user);
        }

    }





}
