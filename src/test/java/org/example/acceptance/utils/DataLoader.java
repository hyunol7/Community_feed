package org.example.acceptance.utils;

import static org.example.acceptance.steps.SignUpAcceptanceSteps.*;
import static org.example.acceptance.steps.UserAcceptanceSteps.createUser;
import static org.example.acceptance.steps.UserAcceptanceSteps.followUser;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.auth.application.dto.CreateUserAuthRequestDto;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.example.user.application.dto.CreateUserRequestDto;
import org.example.user.application.dto.FollowUserRequestDto;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void loadData() {

        //user1,2,3생성
        for(int i=1; i<4; i++){
            createUser("user" + i+"@test.com");
        }

        followUser(new FollowUserRequestDto(1L, 2L));
        followUser(new FollowUserRequestDto(2L, 3L));


        entityManager.createNativeQuery("INSERT INTO community_email_verification (email, token, is_verified) VALUES (?, ?, ?)")
                .setParameter(1, "email@email.com")
                .setParameter(2, "test-token")
                .setParameter(3, false)
                .executeUpdate();

    }

    public String getEmailToken(String email) {
        return (String) entityManager.createNativeQuery("SELECT token FROM community_email_verification WHERE email = ?", String.class)
                .setParameter(1, email)
                .getSingleResult()
                .toString();
    }


    public boolean isEmailVerified(String email) {
        return entityManager.createQuery("SELECT isVerified FROM EmailVerificationEntity WHERE email = :email", Boolean.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public Long getUserId(String email) {
        return entityManager.createQuery("SELECT userId FROM UserAuthEntity WHERE email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    public void createUser(String email){
        requestSendEmail(new SendEmailRequestDto(email));
        String token = getEmailToken(email);
        requestVerifyEmail(email, token);
        registerUser(new CreateUserAuthRequestDto(email, "password", "USER", "name", ""));
    }


}