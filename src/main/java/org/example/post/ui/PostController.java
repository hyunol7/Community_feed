package org.example.post.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.post.application.PostService;
import org.example.post.application.dto.CreatePostRequestDto;
import org.example.post.application.dto.LIkeRequestDto;
import org.example.post.application.dto.UpdatePostRequestDto;
import org.example.post.domain.Post;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public Response<Long> createPost(@RequestBody CreatePostRequestDto dto) {
        Post post = postService.createPost(dto);
        return Response.ok(post.getId());
    }

    @PatchMapping("/{postId}")
    public Response<Long> updatePost(@PathVariable(name = "postId") Long postId,@RequestBody UpdatePostRequestDto dto) {
        Post post = postService.updatePost(postId, dto);
        return Response.ok(post.getId());
    }

    @PostMapping("/like")
    public Response<Long> likePost(@RequestBody LIkeRequestDto dto) {
        postService.likePost(dto);
        return Response.ok(null);
    }

    @PostMapping("/unlike")
    public Response<Long> unlikePost(@RequestBody LIkeRequestDto dto) {
        postService.unLikePost(dto);
        return Response.ok(null);
    }
}
