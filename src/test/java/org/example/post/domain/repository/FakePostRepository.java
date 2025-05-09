package org.example.post.domain.repository;

import org.example.post.domain.Post;
import org.example.post.application.interfaces.PostRepository;
import org.example.post.domain.content.PostContent;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FakePostRepository implements PostRepository {
    private final Map<Long, Post> store = new HashMap<Long, Post>();

    @Override
    public Post save(Post post) {
        if(post.getId() != null){
            store.put(post.getId(), post);
            return post;
        }
        long id = store.size() + 1;
        Post newPost = new Post(id, post.getAuthor(), (PostContent) post.getContentObject());
        store.put(id, newPost);

        return newPost;
    }

    @Override
    public Post findById(Long id) {
        return store.get(id);
    }


}
