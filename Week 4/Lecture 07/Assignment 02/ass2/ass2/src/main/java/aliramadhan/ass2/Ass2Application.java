package aliramadhan.ass2;

import aliramadhan.ass2.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Ass2Application {

    public static void main(String[] args) {
        SpringApplication.run(Ass2Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            // Constructor Injection
            ServiceEmployeeConstructor serviceConstructor = context.getBean(ServiceEmployeeConstructor.class);
            serviceConstructor.notifyEmployee("employee1@example.com", "Constructor Injection", "This is a constructor injection demo.");

            // Field Injection
            ServiceEmployeeField serviceField = context.getBean(ServiceEmployeeField.class);
            serviceField.notifyEmployee("employee2@example.com", "Field Injection", "This is a field injection demo.");

            // Setter Injection
            ServiceEmployeeSetter serviceSetter = context.getBean(ServiceEmployeeSetter.class);
            serviceSetter.notifyEmployee("employee3@example.com", "Setter Injection", "This is a setter injection demo.");
        };
    }
}
