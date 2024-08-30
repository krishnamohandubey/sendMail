package com.mail.emailsend.service;
import com.mail.emailsend.entity.EmailDetails;
import com.mail.emailsend.entity.Recipient;

import java.io.IOException;
import java.util.List;

public interface EmailService {

    String sendSimpleMail(EmailDetails details);
    String sendMailWithAttachment(EmailDetails details);
    void sendEmails(List<Recipient> recipients, String subject) throws IOException;
}
