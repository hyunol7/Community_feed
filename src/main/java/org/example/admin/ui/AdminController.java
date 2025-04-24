package org.example.admin.ui;

import lombok.RequiredArgsConstructor;
import org.example.admin.repository.UserStatusQueryRepositoryImpl;
import org.example.admin.ui.dto.GetTableListResponse;
import org.example.admin.ui.dto.posts.GetPostTableRequestDto;
import org.example.admin.ui.dto.posts.GetPostTableResponseDto;
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

        var result = repository.getDailyRegisterUserStarts(7);
        System.out.println("일별 가입자 수 데이터: " + result);
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

    @GetMapping("/posts")
    public ModelAndView posts(GetPostTableRequestDto dto) {
        System.out.println("✅ 요청 DTO: " + dto);

        ModelAndView mv = new ModelAndView();
        mv.setViewName("posts");

        GetTableListResponse<GetPostTableResponseDto> result =
                adminTableQueryRepository.getPostTableData(dto); // 이 줄에서 오류 가능성 높음

        mv.addObject("requestDto", dto);
        mv.addObject("postList", result.getTableData());
        mv.addObject("totalCount", result.getTotalCount());
        return mv;
    }

}
