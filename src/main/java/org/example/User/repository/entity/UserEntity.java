package org.example.User.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Common.PositivelntegerCounter;
import org.example.User.domain.User;
import org.example.User.domain.UserInfo;

@Entity
@Table(name = "community_user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserEntity {

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
                .followerCount(new PositivelntegerCounter(followerCount))
                .followingCount(new PositivelntegerCounter(followerCount))
                .build();
    }
}
