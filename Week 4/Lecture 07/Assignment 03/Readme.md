# Add Bean Scopes (Singleton, Prototype)

**Bean scopes** in Spring determine the lifecycle and visibility of beans within the Spring container. Here, we’ll focus on:

- **Singleton Scope**: A single instance of the bean is created and shared across the entire application.
- **Prototype Scope**: A new instance of the bean is created each time it is requested.

## 👨‍💻 Implementation

1. [**`EmailService` Interface**](/Lecture%2007/Assignment%2003/task1/ass4/src/main/java/aliramadhan/ass4/service/EmailService.java)

This interface defines the sendEmail method that must be implemented by classes that implement this interface. This method is used to send an email with the parameters to, subject, and body.

### @scope ('prototype')

3. [**`EmployeeServiceConstructor` Class**](/Lecture%2007/Assignment%2003/task1/ass4/src/main/java/aliramadhan/ass4/service/EmailService.javaservice/EmployeeServiceConstructor.java)
   - @Service annotation: Indicates that this class is a Spring service bean managed by a Spring container.
   - The @Scope("singleton") annotation: Specifies that this bean has a singleton scope, which means only one instance of this bean will be created and shared across the application.
   - Dependency Injection with Field (@Autowired): ServiceEmail is injected directly into the emailService field.

### @scope ('singleton')

[**`EmployeeServiceField` Class**](/Lecture%2007/Assignment%2003/task1/ass4/src/main/java/aliramadhan/ass4/service/EmployeeServiceField.java)

- @Service annotation: Indicates that this class is a Spring service bean managed by a Spring container.
- The @Scope("prototype") annotation: Specifies that this bean has a prototype scope, which means that whenever the bean is requested, a new instance will be created.
- Dependency Injection with Constructors: ServiceEmail is injected through a constructor.

[**`Lecture7Application` Class**](/Lecture%2007/Assignment%2003/task1/ass4/src/main/java/aliramadhan/ass4/Ass4Application.java)
Serves as the main application for the Java Spring Boot.

## Testing

![result](/Lecture%2007/Assignment%2003/img/service.png)

# Create a Controller and Test Request Scope

Simple Spring MVC controller that uses a **request-scoped** bean and a new bean instance is created for each HTTP request.

## Request Scope

**Request Scope**: In the Spring context, a bean defined with the request scope will have a new instance created for each HTTP request. This is commonly used in web applications to handle user-specific data during the request processing.

## Implementation

[**`EmployeeController` Class**](/Lecture%2007/Assignment%2003/task2/ass4/src/main/java/aliramadhan/ass4/controller/EmployeeController.java)

- @GetMapping("/notify-constructor"): Defines an endpoint /employee/notify-constructor. When this endpoint is accessed, the notifyEmployeeByConstructor method is called. It uses the employeeServiceConstructor to send a notification.
- @GetMapping("/notify-field"): Defines an endpoint /employee/notify-field. When this endpoint is accessed, the notifyEmployeeByField method is called. It uses the employeeServiceField to send a notification.
- @GetMapping("/notify-setter"): Defines an endpoint /employee/notify-setter. When this endpoint is accessed, the notifyEmployeeBySetter method is called. It uses the employeeServiceSetter to send a notification.

[**`Lecture7Application` Class**](/Lecture%2007/Assignment%2003/task2/ass4/src/main/java/aliramadhan/ass4/controller/EmployeeController.java)
Serves as the main application for the Java Spring Boot.

### Testing

![resutl img](/Lecture%2007/Assignment%2003/img/controller.png)

# How to inject prototype Bean into singleton Bean ?

In Spring, you can inject a prototype-scoped bean into a singleton-scoped bean using a few different approaches. Here are the most common methods:

1. **Using `ObjectFactory` or `Provider`**: The simplest approach using Spring’s built-in `ObjectFactory` or Java’s `Provider` from `javax.inject`.
2. **Using `@Lookup` Method Injection**: A method annotated with `@Lookup` will be overridden by the container to return a new instance of a prototype bean.
3. **Using Application Context**: Manually retrieving the bean from the application context.

## 1. Using @Lookup Annotation

The @Lookup annotation can be used to mark a method as one that returns a bean that should be looked up in the scope of the current request. This allows you to inject a prototype bean into a singleton bean.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    @Lookup
    public PrototypeBean getPrototypeBean() {
        // Spring will override this method to return the correct prototype bean
        return null;
    }

    public void doSomething() {
        PrototypeBean prototypeBean = getPrototypeBean();
        // use prototypeBean
    }
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // prototype bean implementation
}
```

## 2. Using ObjectFactory or Provider Interface

You can use the ObjectFactory or javax.inject.Provider interface to obtain new instances of your prototype bean.

### Using ObjectFactory:

```java
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    @Autowired
    private ObjectFactory<PrototypeBean> prototypeBeanFactory;

    public void doSomething() {
        PrototypeBean prototypeBean = prototypeBeanFactory.getObject();
        // use prototypeBean
    }
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // prototype bean implementation
}
```

### Using Provider:

```java
import javax.inject.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    @Autowired
    private Provider<PrototypeBean> prototypeBeanProvider;

    public void doSomething() {
        PrototypeBean prototypeBean = prototypeBeanProvider.get();
        // use prototypeBean
    }
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // prototype bean implementation
}

```

### 3. Using ApplicationContext

You can manually retrieve the prototype bean from the ApplicationContext.

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class SingletonBean {

    @Autowired
    private ApplicationContext applicationContext;

    public void doSomething() {
        PrototypeBean prototypeBean = applicationContext.getBean(PrototypeBean.class);
        // use prototypeBean
    }
}

@Component
@Scope("prototype")
public class PrototypeBean {
    // prototype bean implementation
}
```

# Diference between BeanFactory and ApplicationContext

## BeanFactory

### Basic Functionality:

It is the simplest container providing basic DI (Dependency Injection) support.
It instantiates a bean when a bean is requested (lazy initialization).

### Lifecycle Management:

Does not manage the complete lifecycle of a bean (such as managing application events and listener registration).

### Usage:

Suitable for lightweight applications where resource consumption is critical.
Used for programmatic access to beans.

### Configuration:

Configurations are typically defined in XML files.

## ApplicationContext

### Advanced Functionality:

It extends BeanFactory to add more enterprise-specific functionality.
It eagerly loads all singleton beans at startup (pre-instantiation).

### Lifecycle Management:

Manages the complete lifecycle of beans, including handling events like context refresh, context shutdown, etc.
Supports internationalization, event propagation, declarative mechanisms to create a bean, etc.

### Usage:

Recommended for most enterprise-level applications.
Supports annotation-based configuration and other modern configurations.

### Additional Features:

Provides support for integrating with Spring AOP.
Has built-in support for different contexts, such as WebApplicationContext for web applications.

### Configuration:

Can be configured using XML files, annotations, and Java-based configuration.

## Summary

- BeanFactory is a basic container with minimal functionality and is suitable for lightweight applications.
- ApplicationContext is a more feature-rich container that provides additional functionalities like lifecycle management, event propagation, and integration with Spring AOP, making it more suitable for enterprise-level applications.
