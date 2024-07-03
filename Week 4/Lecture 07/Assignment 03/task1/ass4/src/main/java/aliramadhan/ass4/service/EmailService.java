package aliramadhan.ass4.service;

public interface EmailService {
    void sendEmail(String to, String subject, String body);
}