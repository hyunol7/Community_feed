package org.example.post.repository.entity.like;

import jakarta.persistence.Column;
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

    @Column(name = "target_id")
    private Long targetId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "target_type")
    private String targetType;

}
