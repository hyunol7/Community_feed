package org.example.auth.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.auth.application.EmailService;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
@RequiredArgsConstructor
public class SignUpController {

    private final EmailService emailService;

    @PostMapping("/send-verification-email")
    public Response<Void> sendEmail(@RequestBody SendEmailRequestDto dto){
        emailService.sendEmail(dto);
        return Response.ok(null);
    }

    @GetMapping("/verify-token")
    public Response<Void> verifyEmail(String email, String token){
        emailService.verifyEmail(email, token);
        return Response.ok(null);
    }
}
