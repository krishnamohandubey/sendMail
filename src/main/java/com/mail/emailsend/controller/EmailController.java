package com.mail.emailsend.controller;

import com.mail.emailsend.entity.EmailDetails;
import com.mail.emailsend.entity.EmailRequest;
import com.mail.emailsend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String mailMail(@RequestBody EmailDetails details){
        return emailService.sendSimpleMail(details);
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
