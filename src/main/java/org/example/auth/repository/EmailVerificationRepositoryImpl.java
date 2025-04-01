package org.example.auth.repository;

import lombok.RequiredArgsConstructor;
import org.example.auth.application.interfaces.EmailVerificationRepository;
import org.example.auth.domain.Email;
import org.example.auth.repository.entity.EmailVerificationEntity;
import org.example.auth.repository.jpa.JpaEmailVerificationRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class EmailVerificationRepositoryImpl implements EmailVerificationRepository {

    private final JpaEmailVerificationRepository jpaEmailVerificationRepository;

    @Override
    public void createEmailVerification(Email email, String token) {
        String emailAddress = email.getEmailText();
        Optional<EmailVerificationEntity> entity = jpaEmailVerificationRepository.findByEmail(emailAddress);

        if (entity.isPresent()) {
            EmailVerificationEntity emailVerificationEntity = entity.get();

            if(emailVerificationEntity.isVerified()){
                throw new IllegalArgumentException("이미 인증된 이메일 입니다");
            }
            return;
        }
        EmailVerificationEntity emailVerificationEntity = new EmailVerificationEntity(emailAddress, token);
        jpaEmailVerificationRepository.save(emailVerificationEntity);
    }




}
