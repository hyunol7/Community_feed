package org.example.admin.ui.query;

import org.example.admin.ui.dto.user.GetDailyRegisterUserResponseDto;

import java.util.List;

public interface UserStatusQueryRepository {
    List<GetDailyRegisterUserResponseDto> getDailyRegisterUserStarts(int beforeDays);
}
