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

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@DynamicUpdate
public class UserEntity  extends TimeBaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String profileImage;
    private Integer followingCount;
    private Integer followerCount;

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
