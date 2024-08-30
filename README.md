# Email Service API

This project provides a RESTful API to send emails using Spring Boot. The API supports sending plain text emails, emails with attachments, and emails using predefined templates.

## Getting Started

To run this application, ensure you have the following:

- Java 11 or higher
- Maven
- SMTP server credentials (for example, Gmail, SendGrid)

### Configuration

Before running the application, configure your SMTP server details in the `application.properties` file:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-email-password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true

### API Endpoints

1. **Send Plain Text Email:**

   POST http://localhost:9000/api/email/sendMail
    {
      "recipient": "abc@gmail.com",
      "msgBody": "Hello, Welcome to the new application of EMail send using spring boot",
      "subject": "Email send using Spring boot"
    }

2. **Send Email with Attachment:**

    POST http://localhost:9000/api/email/sendMailWithAttachment
    {
      "recipient": "abc@gmail.com",
      "msgBody": "Hello Everyone, Please find below attachment of new look photo",
      "subject": "Photo",
      "attachment": "E:/photo.jpeg"
    }

3. **Send Email Using Template**

    POST http://localhost:9000/api/email/sendEmails
    {
      "recipients": [
        {"email": "rABC@gmail.com", "name": "Abc"},
        {"email": "Xyz@gmail.com", "name": "XYZ"}
      ],
      "subject": "Happy Janmashtami"
    }

### Running the Application

  mvn spring-boot:run

### Testing

  curl -X POST http://localhost:9000/api/email/sendMail -H "Content-Type: application/json" -d '{
      "recipient": "abc@gmail.com",
      "msgBody": "Hello, Welcome to the new application of EMail send using spring boot",
      "subject": "Email send using Spring boot"
  }'

### Author
  Krishna mohan dubey - https://github.com/krishnamohandubey



