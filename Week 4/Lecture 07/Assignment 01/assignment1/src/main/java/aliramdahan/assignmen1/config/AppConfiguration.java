package aliramdahan.assignmen1.config;

import aliramdahan.assignmen1.EmployeeWork;
import aliramdahan.assignmen1.model.Employee;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    @Bean
    public Employee employee() {
        Employee employee = new Employee();
        employee.setId("00123");
        employee.setName("Farhan Ali");
        employee.setAge(20);
        employee.setEmployeeWork(employeeWork());
        return employee;
    }
}