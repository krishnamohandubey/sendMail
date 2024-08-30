package com.mail.emailsend.service.impl;

import com.mail.emailsend.entity.EmailDetails;
import com.mail.emailsend.entity.Recipient;
import com.mail.emailsend.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public String sendSimpleMail(EmailDetails details) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(sender);
            simpleMailMessage.setTo(details.getRecipient());
            simpleMailMessage.setSubject(details.getSubject());
            simpleMailMessage.setText(details.getMsgBody());

            javaMailSender.send(simpleMailMessage);
            return "mail send success";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String sendMailWithAttachment(EmailDetails details) {
        MimeMessage mimeMessage =javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper;

        try{
            mimeMessageHelper=new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipient());
            mimeMessageHelper.setText(details.getMsgBody(),true);
            mimeMessageHelper.setSubject(details.getSubject());

            FileSystemResource file =new FileSystemResource(new File(details.getAttachment()));
            mimeMessageHelper.addAttachment(file.getFilename(),file);

            javaMailSender.send(mimeMessage);
            return "mail send success";
        } catch (Exception e) {
            return "Error while Sending Mail";
        }

    }

    @Override
    public void sendEmails(List<Recipient> recipients, String subject) throws IOException {
        // Load the HTML template from the resources directory
        String template = loadEmailTemplate();
        for (Recipient recipient : recipients) {
            try {
                sendEmail(recipient, subject, template);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }

    private void sendEmail(Recipient recipient, String subject, String template) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(recipient.getEmail());
        helper.setSubject(subject);

        // Personalize the template
        String personalizedContent = template.replace("{name}", recipient.getName());
        helper.setText(personalizedContent, true);  // true indicates HTML content

        javaMailSender.send(message);
        System.out.println("mail send success");
    }

    private String loadEmailTemplate() throws IOException {
        ClassPathResource resource = new ClassPathResource("templates/template.html");
        return Files.readAllLines(Paths.get(resource.getURI()))
                .stream()
                .collect(Collectors.joining("\n"));
    }
}
