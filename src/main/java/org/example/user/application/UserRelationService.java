package org.example.user.application;

import org.example.user.application.Interface.UserRelationRepository;
import org.example.user.application.dto.FollowUserRequestDto;
import org.example.user.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserRelationService {

    private final UserRelationRepository userRelationRepository;
    private final UserService userService;


    public UserRelationService(UserService userService,
                UserRelationRepository userRelationRepository) {
        this.userService = userService;
        this.userRelationRepository = userRelationRepository;
    }

    public void follow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }

        user.follow(targetUser);
        userRelationRepository.save(user, targetUser);
    }

    public void unfollow(FollowUserRequestDto dto){
        User user = userService.getUser(dto.userId());
        User targetUser = userService.getUser(dto.targetUserId());

        if(!userRelationRepository.isAlreadyFollow(user, targetUser)){
            throw new IllegalArgumentException();
        }
        user.unfollow(targetUser);
        userRelationRepository.delete(user, targetUser);
    }

}
