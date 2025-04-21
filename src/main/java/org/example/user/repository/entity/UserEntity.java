package org.example.user.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Common.domain.PositiveIntegerCounter;
import org.example.Common.repository.entity.TimeBaseEntity;
import org.example.user.domain.User;
import org.example.user.domain.UserInfo;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
@EntityListeners(AuditingEntityListener.class)
public class UserEntity  extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followingCount;
    private Integer followerCount;
    @CreatedDate
    @Column(updatable = false)
    private LocalDate regDate;

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.name();
        this.profileImage = user.getProfileImage();
        this.followerCount = user.followerCount();
        this.followingCount = user.followingCount();
    }

    public User toUser() {
        return User.builder()
                .id(id)
                .userInfo(new UserInfo(name, profileImage))
                .followerCount(new PositiveIntegerCounter(followerCount))
                .followingCount(new PositiveIntegerCounter(followerCount))
                .build();
    }
}
