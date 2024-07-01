package aliramadhan.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceEmployeeSetter {

    private ServiceEmail emailService;

    @Autowired
    public void setEmailService(ServiceEmail emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String email, String subject, String body) {
        emailService.sendEmail(email, subject, body);
    }}
