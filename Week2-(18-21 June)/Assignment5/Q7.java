package Assignment5;

/*
 * ConcurrentHashMap: It allows concurrent access to the map.
 * ConcurrentHashMap is a thread-safe implementation of the Map interface in Java, which means multiple threads can access it simultaneously without any synchronization issues
 * https://dzone.com/articles/how-concurrenthashmap-works-internally-in-java
 */
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Q7 {
    public static void main(String[] args) {
        // Create a ConcurrentHashMap
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();

        // Create an ExecutorService to manage threads
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Runnable task that increments the count for a key
        Runnable task1 = () -> {
            for (int i = 0; i < 1000; i++) {
                map.merge("key1", 1, Integer::sum);
            }
        };

        // Runnable task that increments the count for another key
        Runnable task2 = () -> {
            for (int i = 0; i < 1000; i++) {
                map.merge("key2", 1, Integer::sum);
            }
        };

        // Submit tasks to the executor
        executor.submit(task1);
        executor.submit(task2);
        executor.submit(task1); // Submitting task1 again to test concurrency
        
        // Shutdown the executor
        executor.shutdown();

        try {
            // Wait for all tasks to finish
            executor.awaitTermination(1, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the map contents
        System.out.println("ConcurrentHashMap content: " + map);
    }
}
