package aliramadhan.ass2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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
