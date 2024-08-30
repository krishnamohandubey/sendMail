package com.mail.emailsend.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    @Email
    private String recipient;
    @NotBlank(message = "message body can't be blank")
    private String msgBody;
    @NotBlank(message = "subject can't be blank")
    private String subject;

    private String attachment;
}
