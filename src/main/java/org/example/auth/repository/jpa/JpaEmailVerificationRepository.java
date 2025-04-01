package org.example.auth.repository.jpa;

import org.example.auth.repository.entity.EmailVerificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaEmailVerificationRepository extends JpaRepository<EmailVerificationEntity, Long> {
    Optional<EmailVerificationEntity> findByEmail(String email);

}
