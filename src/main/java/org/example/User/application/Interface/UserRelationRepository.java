package org.example.User.application.Interface;

import org.example.User.domain.User;

public interface UserRelationRepository {

    boolean isAlreadyFollow(User user, User targetUser);
    void save(User user, User targetUser);
    void delete(User user, User targetUser);
}
