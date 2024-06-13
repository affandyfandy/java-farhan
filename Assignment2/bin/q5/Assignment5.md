# **Thread-Safe Singleton**

## **Implementation of thread-safe singleton**
___

Singleton are created for ensure only on instance can created from class and public access

### **Method 1 `(without volatile)`**

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

    // getters and setters
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
- The method getInstance will return the instance of the Singleton. Only when getInstance is called, and it determines whether the instance is null (i.e., not initialized yet) or not, will it be initialized. After determining whether the instance is null and then rechecking to boost efficiency, the initialization synchronized the thread.