package org.example.auth.ui;

import lombok.RequiredArgsConstructor;
import org.example.Common.ui.Response;
import org.example.auth.application.EmailService;
import org.example.auth.application.dto.SendEmailRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
