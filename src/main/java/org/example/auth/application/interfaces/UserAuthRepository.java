package org.example.auth.application.interfaces;

import org.example.auth.domain.UserAuth;
import org.example.user.domain.User;

public interface UserAuthRepository {
    UserAuth registerUser(UserAuth userAuth, User user);
    UserAuth loginUser(String email, String password);
}
