package org.example.admin.ui.query;

import org.example.admin.ui.dto.GetTableListResponse;
import org.example.admin.ui.dto.posts.GetPostTableRequestDto;
import org.example.admin.ui.dto.posts.GetPostTableResponseDto;
import org.example.admin.ui.dto.user.GetUserTableRequestDto;
import org.example.admin.ui.dto.user.GetUserTableResponseDto;

public interface AdminTableQueryRepository {
    GetTableListResponse<GetUserTableResponseDto> getUserTableData(GetUserTableRequestDto requestDto);
    GetTableListResponse<GetPostTableResponseDto> getPostTableData(GetPostTableRequestDto requestDto);
}
