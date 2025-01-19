package org.example.post.repository.entity.post;

import jakarta.persistence.AttributeConverter;
import org.example.post.domain.content.PostPublicationState;

public class PostPucblicationStateConverter implements AttributeConverter<PostPublicationState, String> {


    @Override
    public String convertToDatabaseColumn(PostPublicationState state) {
        return state.name();
    }

    @Override
    public PostPublicationState convertToEntityAttribute(String s) {
        return PostPublicationState.valueOf(s);
    }

}
