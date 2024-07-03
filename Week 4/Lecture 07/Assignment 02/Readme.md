# Working with Annotations

**Objective**: Create `EmailService` interface and `EmailServiceImpl` class. Add a method for sending email. Create `EmployeeService` class and use `EmailService` with Dependency Injection (DI) to send emails to employees about their work. Demonstrate DI using constructor, field, and setter injection.

## Dependency Injection Methods

1. **Constructor Injection**: This method injects dependencies through the class constructor. This method makes dependencies simple, explicit, and immutable (often preffered).
2. **Field Injection**: This method injects dependencies directly into the fields of the class. This method makes hides dependencies and makes testing harder (less preferred).
3. **Setter Injection**: This method injects dependencies through setter methods. This methos is usefull for optional dependencies or where dependencies are subject to alter while an object is being used.

## 👨‍💻 Implementation

1. [**`EmailService` Interface**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/service/ServiceEmail.java)

This interface defines the sendEmail method that must be implemented by classes that implement this interface. This method is used to send an email with the parameters to, subject, and body.

2. [**`EmailServiceImpl` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/service/ServiceEmailImpl.java)

This class implements ServiceEmail and provides a concrete implementation of the sendEmail method. The @Service annotation indicates that this is a Spring service bean that can be automated injection into other components.

3. [**`EmployeeServiceConstructor` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/service/ServiceEmailImpl.java/EmployeeServiceConstructor.java)

This class uses a constructor for dependency injection. ServiceEmail is injected through a constructor annotated with @Autowired. The notifyEmployee method is used to send email notifications to employees.

4. [**`EmployeeServiceField` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/service/ServiceEmployeeField.java)

This class uses dependency injection through fields. The emailService field is injected directly with the @Autowired annotation.

5. [**`EmployeeServiceSetter` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/service/ServiceEmployeeSetter.java)

This class uses setters for dependency injection. The setEmailService method is annotated @Autowired to inject ServiceEmail dependencies.

6. [**`AppConfig` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/config/AppConfig.java)

   This class is a Spring configuration that uses the @Configuration and @ComponentScan annotations. The @ComponentScan annotation is used to set the base package that Spring will scan for managed components (such as @Service, @Controller, @Repository, etc.).

7. [**`Lecture7Application` Class**](/Lecture%2007/Assignment%2002/ass2/ass2/src/main/java/aliramadhan/ass2/Ass2Application.java)
   Serves as the main application for the Java Spring Boot.

## Testing
![testing](/Lecture%2007/Assignment%2002/asset/result.png)

# Compare (constructor, field, and setter injection)

## 1. Constructor Injection

### Advantages:

- Immutability: Dependencies are provided at the time of object creation, making it easy to make the object immutable.
- Mandatory Dependencies: Ensures that all required dependencies are provided, avoiding the possibility of NullPointerExceptions.
- Testing: Easier to write unit tests since dependencies can be injected through the constructor.

### Disadvantages:

- Verbose: For classes with many dependencies, constructors can become lengthy and hard to read.
- Circular Dependencies: Can be problematic if there are circular dependencies between beans.

Example:

```java
@Service
public class EmployeeService {

    private final EmailService emailService;

    @Autowired
    public EmployeeService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String employeeEmail) {
        emailService.sendEmail(employeeEmail, "Work Update", "Your work details...");
    }
```

}

## 2. Field Injection

### Advantages:

- Simplicity: Less boilerplate code compared to constructor injection, making it concise and easier to read.

### Disadvantages:

- Immutability: The dependencies can be changed after object creation, leading to potential side effects.
- Testing: More difficult to write unit tests since you need reflection or frameworks like Mockito to inject mocks.
- Hidden Dependencies: Dependencies are not visible in the constructor, making it harder to understand the dependencies of a class.

Example:

```java
@Service
public class EmployeeService {

    @Autowired
    private EmailService emailService;

    public void notifyEmployee(String employeeEmail) {
        emailService.sendEmail(employeeEmail, "Work Update", "Your work details...");
    }
```

}

## 3. Setter Injection

### Advantages:

- Optional Dependencies: Good for optional dependencies that may not always be required.
- Readability: Can make code more readable by breaking down dependency injection into manageable pieces.

### Disadvantages:

- Mutability: Dependencies can be changed after object creation, leading to potential side effects.
- Mandatory Dependencies: Does not enforce mandatory dependencies, which can lead to NullPointerExceptions if not set.

Example:

```java
@Service
public class EmployeeService {

    private EmailService emailService;

    @Autowired
    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void notifyEmployee(String employeeEmail) {
        emailService.sendEmail(employeeEmail, "Work Update", "Your work details...");
    }

}
```

## Which One is Better?

1. Constructor Injection is generally considered the best practice because it ensures immutability, mandatory dependencies, and is easier to test. This aligns with the principles of good object-oriented design, such as Dependency Inversion Principle (DIP) and Single Responsibility Principle (SRP).
   Which One is Recommended?
   Constructor Injection is recommended for mandatory dependencies.
2. Setter Injection can be used for optional dependencies.
3. Field Injection is not recommended for production code due to the disadvantages mentioned but can be useful in specific cases like test scenarios or small applications.

## Summary:

1. Use Constructor Injection for mandatory dependencies and to ensure immutability and easier testing.
2. Use Setter Injection for optional dependencies when needed.
3. Avoid Field Injection in favor of the above methods for cleaner, more maintainable, and testable code.

# Research about “Circular dependency injection”

## Definition

Circular dependency injection occurs when two or more components depend on each other directly or indirectly, leading to a cycle. This can be problematic in dependency injection frameworks because it can create infinite loops or other runtime issues.

A circular dependency occurs when a bean A depends on another bean B, and the bean B depends on bean A as well:
`Bean A → Bean B → Bean A`
Of course, we could have more beans implied:
`Bean A → Bean B → Bean C → Bean D → Bean E → Bean A`

## Causes of Circular Dependency Injection

- Direct Circular Dependency: Component A depends on Component B, and Component B depends on Component A.
- Indirect Circular Dependency: Component A depends on Component B, Component B depends on Component C, and Component C depends on Component A.
- Problems Caused by Circular Dependencies
- Initialization Issues: Circular dependencies can cause issues during the initialization of components, as the framework might not be able to resolve the dependencies properly.
- Runtime Errors: If the circular dependencies are not handled correctly, they can lead to runtime errors and crashes.
- Maintenance Difficulties: Circular dependencies make the codebase harder to understand, test, and maintain.

## How to Detect Circular Dependencies

- Static Analysis Tools: Use static analysis tools that can detect circular dependencies in the codebase.
- Manual Code Review: Regular code reviews can help identify and resolve circular dependencies.
- Dependency Graphs: Visualizing dependencies using graphs can help spot circular references.

## Solutions to Circular Dependency Injection

- Refactoring Code: Break down the components to eliminate circular dependencies. This might involve redesigning the architecture to remove the direct or indirect circular references.
- Using Interfaces: Introduce interfaces to decouple the dependencies. Instead of having classes depend on each other directly, they depend on interfaces.
- Dependency Injection Patterns:
- - Constructor Injection: Avoid circular dependencies by injecting dependencies via constructors and ensuring they do not form a cycle.
- - Property Injection: Use property setters for dependencies that might be optional or need to be set after the object is created.
- - Lazy Initialization: Use lazy initialization for dependencies that are expensive to create or might form circular dependencies.
- Factory Pattern: Use a factory class to create instances of components. This can help manage the creation and resolution of dependencies.

# Explain and give examples annotations

## 1. @Configuration

### Explanation: Indicates that the class can be used by the Spring IoC container as a source of bean definitions.

### Example:

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

}
```

## 2. @Bean

### Explanation: Indicates that a method produces a bean to be managed by the Spring container.

### Example:

```java
@Configuration
public class AppConfig {

    @Bean
    public MyService myService() {
        return new MyServiceImpl();
    }

}
```

## 3. @ComponentScan

### Explanation: Configures component scanning directives for use with `@Configuration classes.`

## @Component

### Explanation: Indicates that an annotated class is a "component". Such classes are considered as candidates for auto-detection when using annotation-based configuration and classpath scanning.

### Example :

```java
@Component
public class MyComponent {
}

```

## @Service

### Explanation: Specialization of @Component. It indicates that an annotated class is a service.

### Example :

```java
@Service
public class MyService {
}
```

## @Repository

### Explanation: Specialization of @Component. It indicates that an annotated class is a "Repository" (or "DAO").

```java
@Repository
public class MyRepository {
}
```

## @Autowired

### Explanation: Marks a constructor, field, setter method, or config method as to be autowired by Spring's dependency injection facilities.

```java
@Component
public class MyComponent {

    @Autowired
    private MyService myService;
}

```

## @Scope

### Explanation: Configures the scope of a bean.

```java
@Component
@Scope("prototype")
public class MyPrototypeBean {
}

```

## @Qualifier

### Explanation: Used to distinguish between multiple bean instances when autowiring.

```java
@Component
public class MyComponent {

    @Autowired
    @Qualifier("specificBean")
    private MyService myService;
}

```

## @PropertySource

### Explanation: Provides a mechanism for adding a property source to the Spring environment.

```java
@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {
}

```

## @Value

### Explanation: Indicates a default value expression for the annotated element.

```java
@Component
public class MyComponent {

    @Value("${some.property}")
    private String someProperty;
}

```

## @PreDestroy

### Explanation: Used on methods as a callback notification to signal that the instance is in the process of being removed by the container.

```java
@Component
public class MyComponent {

    @PreDestroy
    public void cleanUp() {
        // Cleanup code
    }
}

```

## @PostConstruct

### Explanation: Used on a method that needs to be executed after dependency injection is done to perform any initialization.

```java
@Component
public class MyComponent {

    @PostConstruct
    public void init() {
        // Initialization code
    }
}
```
