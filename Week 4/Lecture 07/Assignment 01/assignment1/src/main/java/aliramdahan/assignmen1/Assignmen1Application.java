package aliramdahan.assignmen1;

import aliramdahan.assignmen1.config.AppConfiguration;
import aliramdahan.assignmen1.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class Assignmen1Application {

	public static void main(String[] args) {
//		SpringApplication.run(Assignmen1Application.class, args);
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		Employee employee = context.getBean(Employee.class);
		employee.working();
	}

}
