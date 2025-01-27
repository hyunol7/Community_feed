package org.example.post.domain.repository;

import org.example.post.domain.comment.Comment;
import org.example.post.application.interfaces.CommentRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeCommentRepository implements CommentRepository {

    private  final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if(comment.getId() != null){
            store.put(comment.getId(), comment);
            return comment;
        }

        long id = store.size() + 1;
        Comment newComment = new Comment(id,comment.getAuthor(),  comment.getContentContent(), comment.getPost());
        store.put(id, newComment);
        return newComment;
    }

    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }


}
