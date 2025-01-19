package org.example.post.application;

import org.example.user.application.UserService;
import org.example.user.domain.User;
import org.example.post.domain.Post;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.interfaces.LIkeRepository;
import org.example.post.application.interfaces.PostRepository;

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
        return postRepository.findById(id).orElseThrow(()->new IllegalArgumentException("this is a test post"));
    }

public Post createPost(CreatePostRequestDto dto){
    User author = userService.getUser(dto.userId());
    Post post = Post.createPost(null, author, dto.content(), dto.state());
    return postRepository.save(post);
}

    public Post updatePost(Long id, CreatePostRequestDto dto){
        Post post = getPost(id);
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
