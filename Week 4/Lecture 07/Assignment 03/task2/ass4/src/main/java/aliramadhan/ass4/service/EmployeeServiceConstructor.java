package aliramadhan.ass4.service;

import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Scope;

@Service
@Scope("prototype")
public class EmployeeServiceConstructor {
    private final ServiceEmail emailService;

    public EmployeeServiceConstructor(ServiceEmail emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String employeeEmail) {
        System.out.println("This uses constructor DI.");
        emailService.sendEmail(employeeEmail, "New assignment", "There are new assignments for lecture 7. \n");
    }
}