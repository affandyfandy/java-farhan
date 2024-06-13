# **Thread-Safe Singleton**

## **Implementation of thread-safe singleton**

---

### *Singleton are created for ensure only on instance can created from class and public access*

## **Eager Initialization**

---
In the eager initialization approach, the Singleton instance is **created at the time of class loading**. This ensures thread safety but may not be memory-efficient if the Singleton is not used immediately.
Eager initialization work fine in a single-threaded environment. However, they won't be suitable for multi-threaded environments.

```java
public class EagerInitializedSingleton {
    private static final EagerInitializedSingleton instance = new EagerInitializedSingleton();

    private EagerInitializedSingleton() { }

    public static EagerInitializedSingleton getInstance() {
        return instance;
    }
}
```

## **Lazy Initialization**

---

Lazy Initialization (Synchronized Method)
In this approach, the Singleton instance is **created only when it’s requested for the first time**. It uses a synchronized method to ensure thread safety.

```java
public class LazyInitializedSingleton {
    private static LazyInitializedSingleton instance;

    private LazyInitializedSingleton() { }

    public static synchronized LazyInitializedSingleton getInstance() {
        if (instance == null) {
            instance = new LazyInitializedSingleton();
        }
        return instance;
    }
}
```

## **Double-Checked Locking**

---

This technique combines lazy initialization and double-checked locking to improve performance by avoiding synchronization once the instance is created.

```java
public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;

    private DoubleCheckedLockingSingleton() { }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
```

This technique uses Lazy initialization and provides improved performance but it requires careful implementation to avoid subtle issues.

## **Bill Pugh Singleton**

---

The Bill Pugh Singleton, also known as the **Initialization-on-demand holder idiom**, is a clever way to ensure thread safety in a Singleton without requiring explicit synchronization.*It means this pattern use inner static helper class that only have one singlet while needed* Here’s how it works:
<!-- It leverages the fact that static nested classes are not loaded until they are referenced, ensuring safe lazy initialization.  -->


- Inner Static Class: In the Bill Pugh Singleton pattern, the Singleton instance is nested within a private static inner class. This inner class is not loaded until it is referenced.
- Lazy Initialization: The Singleton instance is created when the inner class is first referenced. This ensures that the instance is only created when it is needed, which is a form of lazy initialization.
- Static Final Instance: The Singleton instance is declared as a static final variable in the inner class. This guarantees that it is initialized only once, and subsequent access to it returns the same instance.

```java
public class BillPughSingleton {
    private BillPughSingleton() {}

    private static class SingletonHelper {
        private static final BillPughSingleton INSTANCE = new BillPughSingleton();
    }

    public static BillPughSingleton getInstance() {
        return SingletonHelper.INSTANCE;
    }
}
```

The Bill Pugh Singleton pattern is a recommended way to create thread-safe singletons in Java, and it avoids synchronization overhead until the Singleton instance is actually needed.

## **Singleton Using Enum**

Enums in Java are more than constants because they can encapsulate behavior, making them versatile.

The Java enum type itself provides inherent thread-safety, as enum values are effectively singletons by design. They are initialized once, when the enum class is loaded, and they cannot be instantiated again. *for example, the day in week* so the data cannot outside from the day in week

Enums is a data type that allows a variable to be selected from a set of predefined values. Enums are useful for improving code readability and reducing the chance of errors
<!-- Using an enum for Singleton patterns is not only concise but also more resistant to several Singleton-related issues, such as reflection-based attacks and serialization problems, which other Singleton implementations may require additional effort to address. -->
**Enums provides implicit support for thread safety and only one instance is guaranteed**

```java
import java.security.SecureRandom;

public enum OtpService {
    INSTANCE;

    private static class OTPService {
        private final SecureRandom random = new SecureRandom();
        private final int otpLength = 6;

        public String generateOtp() {
            StringBuilder otp = new StringBuilder();
            for (int i = 0; i < otpLength; i++) {
            a random digit (0-9)
            }
            return otp.toString();
        }

        public boolean verifyOtp(String providedOtp, String expectedOtp) {
            return providedOtp.equals(expectedOtp);
        }
    }

    private final OTPService otpService = new OTPService();

    public String generateOtp() {
        return otpService.generateOtp();
    }

    public boolean verifyOtp(String providedOtp, String expectedOtp) {
        return otpService.verifyOtp(providedOtp, expectedOtp);
    }
}
```

## Singleton Implementation Comparison

| Method                 | Simplicity  | Performance                           |
| ---------------------- | ----------- | ------------------------------------- |
| Synchronized           | Moderate    | Low (due to synchronization overhead) |
| Double-Checked Locking | Complex     | Moderate (lower overhead)             |
| Bill Pugh              | Simple      | High (no synchronization overhead)    |
| Enum                   | Very Simple | High (handled by JVM)                 |
