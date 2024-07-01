package aliramadhan.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;

public class ServiceEmployeeConstructor {

    private final ServiceEmail emailService;

    @Autowired
    public ServiceEmployeeConstructor(ServiceEmail emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String email, String subject, String body) {
        emailService.sendEmail(email, subject, body);
    }
}
