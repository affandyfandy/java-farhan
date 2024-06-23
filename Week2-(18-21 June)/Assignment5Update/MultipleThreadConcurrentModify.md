# Concurrent Modification Exception

If multiple threads access and modify a collection like ArrayList concurrently, without synchronization, several problems will happen:

- Inconsistent State: One thread may read or write to the collection while another thread is modifying it, leading to an inconsistent or corrupted state. The outcome of operations depends on the timing of threads' execution.
- The ConcurrentModificationException is thrown when a collection is modified while it is being iterated over by multiple threads, or by the same thread in some cases while using standard non-concurrent collections like ArrayList, HashSet, etc.. So the collection has been modified after the iterator was created has detected , this modification is doesn't work through the iterator itself

This is a simple example that demonstrates `ConcurentModificationException`

```java
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ConcurrentModificationExceptionExample {

    public static void main(String[] args) throws InterruptedException {
        List<String> myList = new ArrayList<>();
        myList.add("1");
        myList.add("2");
        myList.add("3");
        myList.add("4");
        myList.add("5");

        // Thread to iterate over the list
        Thread iteratorThread = new Thread(() -> {
            try {
                Iterator<String> it = myList.iterator();
                while (it.hasNext()) {
                    String value = it.next();
                    System.out.println("Iterator Thread - List Value: " + value);
                    Thread.sleep(50); // Adding a small delay to increase chance of concurrent modification
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // Thread to modify the list
        Thread modifierThread = new Thread(() -> {
            try {
                Thread.sleep(100); // Adding a small delay to ensure the iterator starts first
                myList.remove("3"); // This should cause a ConcurrentModificationException
                System.out.println("Modifier Thread - Removed value 3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        iteratorThread.start();
        modifierThread.start();

        iteratorThread.join();
        modifierThread.join();
    }
}

```

To handle these issues, several approaches can be used:

Synchronization: Synchronize the access to the collection using synchronized blocks or methods to ensure that only one thread can modify or read the collection at a time.

```java
List<Integer> list = Collections.synchronizedList(new ArrayList<>());
    synchronized (list) {
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
        System.out.println(iterator.next());
    }
}
```

### `Concurrent Collections`: 
Use concurrent collections from the java.util.concurrent package such as ConcurrentHashMap, CopyOnWriteArrayList, or ConcurrentLinkedQueue which are designed for concurrent access.

```java
    List<Integer> list = new CopyOnWriteArrayList<>();
    list.add(1);
    list.add(2);
    for (Integer i : list) {
        System.out.println(i);
    }
```

<!-- Explicit Locks: Use ReentrantLock to provide more granular control over synchronization, allowing more complex concurrency control scenarios than synchronized blocks.

```java
List<Integer> list = new ArrayList<>();
ReentrantLock lock = new ReentrantLock();
lock.lock();
try {
    list.add(1);
    list.add(2);
} finally {
    lock.unlock();
}
```

Atomic Variables: For simple operations on single variables, use atomic classes like AtomicInteger, AtomicLong, or AtomicReference to ensure atomicity without using explicit synchronization.

```java
AtomicInteger atomicInteger = new AtomicInteger(0);
atomicInteger.incrementAndGet();
```

Avoid Shared State: Design the application to avoid shared mutable state where possible. Instead, use immutable objects or pass copies of objects to avoid direct modification.

```java
List<Integer> list = Collections.unmodifiableList(Arrays.asList(1, 2, 3));
for (Integer i : list) {
System.out.println(i);
}
``` -->
