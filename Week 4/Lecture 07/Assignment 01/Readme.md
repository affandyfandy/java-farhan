# Advantages and Drawbacks of Dependency Injection (DI):

## Definition

Dependency Injection (DI) is a technique in programming that allows objects to get the dependencies they need from external sources rather than creating them themselves. DI helps increase modularity and allows programme components to be more easily tested and maintained.

## How DI Works

Looks at a simple analogy

Assume the role of a coffee shop owner. Rather than producing its own coffee beans (dependence), the coffee shop orders its coffee beans from a source. You only need to switch suppliers to alter the sort of beans; the coffee shop's interior remains unchanged.

In programming, this means that a class (like the coffee shop) receives its dependencies (like coffee beans) from an external source rather than creating them itself.

## Advantages:

---

- Decoupling: DI promotes loose coupling between classes, making the code more modular and easier to manage.
- Ease of Testing: DI makes unit testing easier by allowing dependencies to be injected as mocks or stubs.
- Flexibility: Allows for flexible configurations and swapping of implementations without changing the dependent code.
- Maintainability: Enhances the maintainability of code by following SOLID principles, particularly the Dependency Inversion Principle.
- Lifecycle Management: DI containers can manage the lifecycle of dependencies, ensuring proper initialization and destruction.

## Drawbacks:

Complexity: DI frameworks can introduce complexity, especially for beginners or small projects.
Learning Curve: Understanding DI and using DI frameworks effectively requires time and effort.
Overhead: There can be a performance overhead associated with dependency injection, though often negligible in most applications.
Debugging Difficulty: Debugging issues related to DI can sometimes be challenging, particularly in large applications with complex dependency graphs.

# Create Employee Class and Convert XML Bean Declaration to Java Configuration

## Configuration Conversion

### Original XML Configuration

```java
<beans>
    <bean id="employee" class="com.helen.demo.entity.Employee">
        <constructor-arg name="name" value="GL" />
        <constructor-arg name="employeeWork">
            <bean class="com.helen.demo.EmployeeWork" />
        </constructor-arg>
    </bean>
</beans>
```

### Equivalent Java Configuration

```java
package com.helen.demo.config;

import com.helen.demo.entity.Employee;
import com.helen.demo.EmployeeWork;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeWork employeeWork() {
        return new EmployeeWork();
    }

    @Bean
    public Employee employee() {
        return new Employee("GL", employeeWork());
    }

}
```

# Setter-Based Dependency Injection Using @Configuration

```java
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
```

Setter-Based Injection: Allows dependencies to be changed or injected after the object is created, offering more flexibility but potentially leading to less immutability.
