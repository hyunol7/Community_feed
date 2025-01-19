package org.example.user.application.Interface;

import org.example.user.domain.User;

public interface UserRepository {

    User save(User user);
    User findById(Long id);

}
