package org.example.post.repository.entity.like;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.example.Common.repository.entity.TimeBaseEntity;

@Embeddable
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class LIkeIdEntity extends TimeBaseEntity {

    private Long targetId;
    private Long userId;
    private String targetType;

}
