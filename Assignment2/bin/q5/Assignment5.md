# **Thread-Safe Singleton**

## **Implementation of thread-safe singleton**

---

### **Singleton are created for ensure only on instance can created from class and public access**

<!-- ### **Method 1 `(without volatile)`** -->
<!--
```
public final class Singleton {

    private static Singleton INSTANCE;
    private String info = "Initial info class";

    private Singleton() {
    }

    public static Singleton getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Singleton();
        }

        return INSTANCE;
    }

}
```
- Constructors are made private so there will be no initiation on public or out-of-class classes
- This method uses `lazy initialization`, meaning that an instance of the singleton will be created the first time getInstance() is called.
- The method getInstance will synchronized first before return the instance to ensure only one thread at one time can access this method.
- The method getInstance will return the instance of the Singleton. It will be initialized only if the getInstance called by checking if the instance is null (still not initialized yet) or not.

### **Method 2 `(with volatile)`**
```
public class Singleton {
    private static volatile Singleton instance;

    private Singleton() {}

    public Singleton getInstance() {
        if (instance == null) {
            synchronized(this){
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
```
- Constructors are made private so there will be no initiation on public or out-of-class classes
- The instance have volatile keyword so all threads can see the change of Singleton instance.
- Double-Checked Locking with Synchronization: In the getInstance() block, there is double-checked locking using synchronized:
- The method getInstance will return the instance of the Singleton. Only when getInstance is called, and it determines whether the instance is null (i.e., not initialized yet) or not, will it be initialized. After determining whether the instance is null and then rechecking to boost efficiency, the initialization synchronized the thread. -->

## **Eager Initialization**

---

In the eager initialization approach, the Singleton instance is created at the time of class loading. This ensures thread safety but may not be memory-efficient if the Singleton is not used immediately.

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
In this approach, the Singleton instance is created only when it’s requested for the first time. It uses a synchronized method to ensure thread safety.

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

The Bill Pugh Singleton, also known as the Initialization-on-demand holder idiom, is a clever way to ensure thread safety in a Singleton without requiring explicit synchronization. It leverages the fact that static nested classes are not loaded until they are referenced, ensuring safe lazy initialization. Here’s how it works:

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

The Java enum type itself provides inherent thread-safety, as enum values are effectively singletons by design. They are initialized once, when the enum class is loaded, and they cannot be instantiated again.

Using an enum for Singleton patterns is not only concise but also more resistant to several Singleton-related issues, such as reflection-based attacks and serialization problems, which other Singleton implementations may require additional effort to address.

```
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

public class Main {
    public static void main(String[] args) {
        OtpService otpService = OtpService.INSTANCE;

        String otp = otpService.generateOtp();
        System.out.println("Generated OTP: " + otp);

    user input
        boolean isOtpValid = otpService.verifyOtp(userEnteredOtp, otp);

        if (isOtpValid) {
            System.out.println("OTP is valid.");
        } else {
            System.out.println("OTP is invalid.");
        }
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
