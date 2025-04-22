package org.example.admin.ui;

import lombok.RequiredArgsConstructor;
import org.example.admin.repository.UserStatusQueryRepositoryImpl;
import org.example.admin.ui.dto.GetTableListResponse;
import org.example.admin.ui.dto.user.GetUserTableRequestDto;
import org.example.admin.ui.dto.user.GetUserTableResponseDto;
import org.example.admin.ui.query.AdminTableQueryRepository;
import org.example.admin.ui.query.UserStatusQueryRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserStatusQueryRepository repository;
    private final AdminTableQueryRepository adminTableQueryRepository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        mv.addObject("result", repository.getDailyRegisterUserStarts(7));
        return mv;
    }

    @GetMapping("/users")
    public ModelAndView users(GetUserTableRequestDto dto) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("users");

        GetTableListResponse<GetUserTableResponseDto> result = adminTableQueryRepository.getUserTableData(dto);
        mv.addObject("requestDto", dto);
        mv.addObject("userList", result.getTableData());
        mv.addObject("totalCount", result.getTotalCount());
        return mv;
    }
}
