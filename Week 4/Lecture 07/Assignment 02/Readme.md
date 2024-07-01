#

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
