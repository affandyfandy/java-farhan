package aliramadhan.ass2.service;

import org.springframework.stereotype.Service;

@Service
public class ServiceEmailImpl implements ServiceEmail {
    @Override
    public void sendEmail(String to, String subject, String body) {
        System.out.println("Sending email to: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
    }
}

