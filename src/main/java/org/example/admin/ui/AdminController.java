package org.example.admin.ui;

import lombok.RequiredArgsConstructor;
import org.example.admin.repository.UserStatusQueryRepositoryImpl;
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
    private final UserStatusQueryRepositoryImpl userStatusQueryRepositoryImpl;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");

        mv.addObject("result", userStatusQueryRepositoryImpl.getDailyRegisterUserStarts(7));
        return mv;
    }
}
