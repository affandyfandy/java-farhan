# 🛠️ Stream Paralel

## 📝 Definition

Java Streams is a powerful feature introduced in Java 8 that simplifies the processing of collections and sequences of data. Streams enable to perform complex operations on data with concise and expressive code. such as `filtering, mapping, and reducing on a collection of data`. Streams can be used with various data sources, including `arrays, collections, and even I/O channels`. and easy to create streams that execute in parallel and make use of multiple processor cores.

### 🪨 Process type stream divide by two

- ### Paralel
- ### Sequential
  ![Process Stream](/Assignment6/assets/process.png)

### (Sequential streams) process elements one by one sequentially

### (Paralel streams) process elements simultaneously using multiple threads

## ⏳ When to use paralel streams

Use paralel system must consider 2 things :

- dataset size
- computationally intensive operations

However, there are important considerations to use paralel system

- Dataset Size: Parallel streams are most effective with large datasets. For small datasets, the overhead of managing multiple threads may outweigh the benefits.
- Operation Complexity: Parallel streams work well with operations that are CPU-bound (i.e., intensive computations) rather than I/O-bound (e.g., reading from a file or network).
- Thread-Safety: Ensure that the operations performed within the parallel stream are thread-safe. Shared mutable data can lead to race conditions and inconsistent results.
- Order of Processing: If the order of processing matters, parallel streams might not be suitable, as elements are processed concurrently and not in a specific order.
- System Resources: Ensure that the system has enough CPU cores to handle parallel processing. On systems with limited cores, the performance gain may be minimal.

## 🧑‍💻 Example: Summing a List of Numbers Using Parallel Stream

```java
import java.util.Arrays;
import java.util.List;

public class ParallelStreamSum {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Using sequential stream to sum the numbers
        int sequentialSum = numbers.stream()
                                   .reduce(0, Integer::sum);
        System.out.println("Sum using sequential stream: " + sequentialSum);

        // Using parallel stream to sum the numbers
        int parallelSum = numbers.parallelStream()
                                 .reduce(0, Integer::sum);
        System.out.println("Sum using parallel stream: " + parallelSum);
    }
}

```
