package org.example.post.domain.repository;

import org.example.Common.domain.PositiveIntegerCounter;
import org.example.post.domain.comment.Comment;
import org.example.post.application.interfaces.CommentRepository;

import java.util.HashMap;
import java.util.Map;

public class FakeCommentRepository implements CommentRepository {

    private  final Map<Long, Comment> store = new HashMap<>();

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == null) {
            long id = store.size() + 1;

            comment = Comment.builder()
                    .id(id)
                    .post(comment.getPost())
                    .author(comment.getAuthor())
                    .content(comment.getContentContent())
                    .likeCount(new PositiveIntegerCounter(comment.getLikeCount()))
                    .build();
        }

        store.put(comment.getId(), comment);
        return comment;
    }



    @Override
    public Comment findById(Long id) {
        return store.get(id);
    }


}
