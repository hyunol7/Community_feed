package org.example.User.application;

import org.example.User.application.Interface.UserRelationRepository;
import org.example.User.application.dto.FollowUserRequestDto;
import org.example.User.domain.User;

public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;


    public UserRelationService(UserService userService,
                UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.UserId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.UserId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }
        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

}
