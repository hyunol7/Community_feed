package org.example.auth.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.Common.repository.entity.TimeBaseEntity;
import org.example.auth.domain.UserAuth;

import java.time.LocalDateTime;

@Entity
@Table(name = "community_user_auth")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserAuthEntity extends TimeBaseEntity {

    @Id
    private String email;
    private String password;
    private String role;
    private Long userId;
    private LocalDateTime lastLoginAt;

    public UserAuthEntity(UserAuth userAuth, Long userId) {
        this.email = userAuth.getEmail();
        this.password = userAuth.getPassword();
        this.role = userAuth.getUserRole();
        this.userId = userId;

    }

    public UserAuth toUserAuth() {
        return new UserAuth(email, password, role, userId);
    }

        public void updateLastLoginAt(){
            lastLoginAt = LocalDateTime.now();

    }
}

