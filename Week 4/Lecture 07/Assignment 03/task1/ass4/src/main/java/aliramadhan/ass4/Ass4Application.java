package aliramadhan.ass4;

import aliramadhan.ass4.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


@SpringBootApplication
public class Ass4Application {
	public static void main(String[] args) {
		SpringApplication.run(Ass4Application.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(aliramadhan.ass4.config.AppConfig.class);

		testConstructorDI(context);
		testFieldDI(context);
		testSetterDI(context);
	}

	private static void testConstructorDI(ApplicationContext context) {
		EmployeeServiceConstructor employeeServiceConstructor1 = context.getBean(EmployeeServiceConstructor.class);
		EmployeeServiceConstructor employeeServiceConstructor2 = context.getBean(EmployeeServiceConstructor.class);
		System.out.println("Constructor DI (employeeServiceConstructor1): " + employeeServiceConstructor1);
		System.out.println("Constructor DI (employeeServiceConstructor2): " + employeeServiceConstructor2);
		System.out.println("Are employeeServiceConstructor1 and employeeServiceConstructor2 the same? " +
				(employeeServiceConstructor1 == employeeServiceConstructor2) + "\n");
	}

	private static void testFieldDI(ApplicationContext context) {
		EmployeeServiceField employeeServiceField1 = context.getBean(EmployeeServiceField.class);
		EmployeeServiceField employeeServiceField2 = context.getBean(EmployeeServiceField.class);
		System.out.println("Field DI (employeeServiceField1): " + employeeServiceField1);
		System.out.println("Field DI (employeeServiceField2): " + employeeServiceField2);
		System.out.println("Are employeeServiceField1 and employeeServiceField2 the same? " +
				(employeeServiceField1 == employeeServiceField2) + "\n");
	}

	private static void testSetterDI(ApplicationContext context) {
		EmployeeServiceSetter employeeServiceSetter1 = context.getBean(EmployeeServiceSetter.class);
		EmployeeServiceSetter employeeServiceSetter2 = context.getBean(EmployeeServiceSetter.class);
		System.out.println("Setter DI (employeeServiceSetter1): " + employeeServiceSetter1);
		System.out.println("Setter DI (employeeServiceSetter2): " + employeeServiceSetter2);
		System.out.println("Are employeeServiceSetter1 and employeeServiceSetter2 the same? " +
				(employeeServiceSetter1 == employeeServiceSetter2) + "\n");
	}
}