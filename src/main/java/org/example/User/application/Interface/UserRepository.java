package org.example.User.application.Interface;

import org.example.User.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);
    Optional<User> findById(Long id);


}
