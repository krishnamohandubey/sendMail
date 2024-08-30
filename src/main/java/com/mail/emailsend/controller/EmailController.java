package com.mail.emailsend.controller;

import com.mail.emailsend.advice.ApiError;
import com.mail.emailsend.advice.ApiResponse;
import com.mail.emailsend.entity.EmailDetails;
import com.mail.emailsend.entity.EmailRequest;
import com.mail.emailsend.service.EmailService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/email")
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/sendMail")
    public ResponseEntity<ApiResponse> mailMail(@RequestBody @Valid EmailDetails details){
        String status= emailService.sendSimpleMail(details);
        ApiResponse apiResponse = ApiResponse
                .builder()
                .status(HttpStatus.OK)
                .message(status)
                .build();
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/sendMailWithAttachment")
    public String mailMailWithAttachment(@RequestBody EmailDetails details){
        return emailService.sendMailWithAttachment(details);
    }

    @PostMapping("/sendEmails")
    public String sendEmails(@RequestBody EmailRequest emailRequest) throws IOException {
        try{
            emailService.sendEmails(emailRequest.getRecipients(), emailRequest.getSubject());
            return "Emails sent successfully!";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
