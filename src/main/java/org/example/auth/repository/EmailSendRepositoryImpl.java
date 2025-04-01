package org.example.auth.repository;

import org.example.auth.application.interfaces.EmailSendRepository;
import org.example.auth.domain.Email;
import org.springframework.stereotype.Repository;

@Repository
public class EmailSendRepositoryImpl implements EmailSendRepository {

    @Override
    public void sendEmail(Email email, String randomToken) {

    }
}
