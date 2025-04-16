package org.example.auth.repository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.auth.application.interfaces.UserAuthRepository;
import org.example.auth.domain.UserAuth;
import org.example.auth.repository.entity.UserAuthEntity;
import org.example.auth.repository.jpa.JpaUserAuthRepository;
import org.example.user.application.Interface.UserRepository;
import org.example.user.domain.User;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserAuthRepositoryImpl implements UserAuthRepository {

    private final JpaUserAuthRepository jpaUserAuthRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserAuth registerUser(UserAuth userAuth, User user) {
        User savedUser = userRepository.save(user);
        UserAuthEntity userAuthEntity = new UserAuthEntity(userAuth, savedUser.getId());
        userAuthEntity = jpaUserAuthRepository.save(userAuthEntity);
        return userAuthEntity.toUserAuth();
    }

    @Override
    public UserAuth loginUser(String email, String password) {
        UserAuthEntity userAuthEntity = jpaUserAuthRepository.findById(email).orElseThrow();
        UserAuth userAuth = userAuthEntity.toUserAuth();

        if(!userAuth.matchPassword(password)) {
            throw new IllegalArgumentException("Invalid password");
        }
        return null;
    }
}
