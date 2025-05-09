package org.example.auth.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.auth.application.AuthService;
import org.example.auth.application.EmailService;
import org.example.auth.application.dto.CreateUserAuthRequestDto;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.example.user.application.dto.CreateUserRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;
    private final AuthService authService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto){
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-token")
    public Response<Void> verifyEmail(String email, String token){
      //  emailService.verifyEmail(email, token);
        return Response.ok(null);
    }

    @PostMapping("/register")
    public Response<Long> register(@RequestBody CreateUserAuthRequestDto dto){
        return Response.ok(authService.registerUser(dto));
    }


}
