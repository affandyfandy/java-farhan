package aliramadhan.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceEmployeeField {

    @Autowired
    private ServiceEmail emailService;

    public void notifyEmployee(String email, String subject, String body) {
        emailService.sendEmail(email, subject, body);
    }
}
