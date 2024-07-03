package aliramadhan.ass4.controller;

import aliramadhan.ass4.service.EmployeeServiceConstructor;
import aliramadhan.ass4.service.EmployeeServiceField;
import aliramadhan.ass4.service.EmplyeeServiceSetter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeServiceConstructor employeeServiceConstructor;
    private final EmployeeServiceField employeeServiceField;
    private final EmplyeeServiceSetter employeeServiceSetter;

    @Autowired
    public EmployeeController(EmployeeServiceConstructor employeeServiceConstructor,
                              EmployeeServiceField employeeServiceField,
                              EmplyeeServiceSetter employeeServiceSetter) {
        this.employeeServiceConstructor = employeeServiceConstructor;
        this.employeeServiceField = employeeServiceField;
        this.employeeServiceSetter = employeeServiceSetter;
    }

    @GetMapping("/notify-constructor")
    public String notifyEmployeeByConstructor() {
        employeeServiceConstructor.notifyEmployee("employee@example.com");
        return "Notification sent via constructor injection!";
    }

    @GetMapping("/notify-field")
    public String notifyEmployeeByField() {
        employeeServiceField.notifyEmployee("employee@example.com");
        return "Notification sent via field injection!";
    }

    @GetMapping("/notify-setter")
    public String notifyEmployeeBySetter() {
        employeeServiceSetter.notifyEmployee("employee@example.com");
        return "Notification sent via setter injection!";
    }
}

