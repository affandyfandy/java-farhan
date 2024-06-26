# Issue Concurrent

```java
    ArrayList<String> list = new ArrayList<String>();// Creating arraylist
    list.add("Mango");// Adding object in arraylist
    list.add("Apple");
    list.add("Banana");
    list.add("Grapes");
    // Printing the arraylist object
    for (String s1 : list) {
        if (s1.equals("Apple")) {
            // s1.remove(s1)
            System.out.println("found " + s1);
        }
    }
```

## Issues

---

`this code` will be error because in string not any method remove
![Issue Picture](/Assignment5Update/assets/issue.png)
because
because if use the enhanced for loop , its iterator cannot modify directly or CRUD, but only reading
if try to modify the element when loop continous , will be cause `ConcurrentModificationException`

## Solution

---

- Using Iterators: You can use Iterators to avoid ConcurrentModificationException.
  `Iterators can access and modify` while data is looping, like ArrayList and HashSet

- Use of Exception Handling (Optional): Exception handling can be used to catch errors, but it is better to prevent errors with the right approach.
  use this way for make better handling

- Using `removeIf` Method (Java 8 and later) : The removeIf method is a convenient way to remove elements from a list that satisfy a certain condition.

- Using a `ListIterator` for Lists, ListIterator allows for more control over the iteration process, including the ability to remove elements safely without causing ConcurrentModificationException.

### **Iterator**

```java
 public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<String>()
    list.add("Mango");
    list.add("Apple");
    list.add("Banana");
    list.add("Grapes");
    System.out.println(list);

    Iterator<String> iterator = list.iterator();
    while (iterator.hasNext()) {
        String s1 = iterator.next();
        if (s1.equals("Apple")) {
            iterator.remove();
        }
    }

    System.out.println(list);

    int index = 0;
    for (String s1 : list) {
        System.out.println("Index: " + index + ", Element: " + s1);
        index++;
    }
}
```

### **ConcurrentModificationHandling Handling**

```java
public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<String>();// Creating arraylist
    list.add("Mango");// Adding object in arraylist
    list.add("Apple");
    list.add("Banana");
    list.add("Grapes");
    // Printing the arraylist object
    System.out.println(list);

    try {
        // Trying to modify the list during iteration using enhanced for loop
        for (String s1 : list) {
            if (s1.equals("Apple")) {
                list.remove(s1); // This will cause ConcurrentModificationException
            }
        }
    } catch (ConcurrentModificationException e) {
        System.out.println("Caught ConcurrentModificationException: " + e.getMessage());
    }

    System.out.println(list);

    int index = 0;
    for (String s1 : list) {
        System.out.println("Index: " + index + ", Element: " + s1);
        index++;
    }
}
```

We can also combine for with iterator and exception handling , use try-catch

```java
 public static void main(String[] args) {
    ArrayList<String> list = new ArrayList<String>();// Creating arraylist
    list.add("Mango");// Adding object in arraylist
    list.add("Apple");
    list.add("Banana");
    list.add("Grapes");
    // Printing the arraylist object
    System.out.println(list);

    try {
        // Using Iterator to remove elements
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            String s1 = iterator.next();
            if (s1.equals("Apple")) {
                iterator.remove();
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }

    System.out.println(list);

    int index = 0;
    for (String s1 : list) {
        System.out.println("Index: " + index + ", Element: " + s1);
        index++;
    }
}
```

### **removeIf**

```java
import java.util.ArrayList;

public class RemoveIfExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Mango");
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");

        System.out.println("Original list: " + list);

        // Remove elements that match the condition
        list.removeIf(s -> s.equals("Apple"));

        System.out.println("Updated list: " + list);

        int index = 0;
        for (String s : list) {
            System.out.println("Index: " + index + ", Element: " + s);
            index++;
        }
    }
}
```

### listiterator

```java
import java.util.ArrayList;
import java.util.ListIterator;

public class ListIteratorExample {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Mango");
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");

        System.out.println("Original list: " + list);

        // Using ListIterator to remove elements
        ListIterator<String> listIterator = list.listIterator();
        while (listIterator.hasNext()) {
            String s = listIterator.next();
            if (s.equals("Apple")) {
                listIterator.remove();
            }
        }

        System.out.println("Updated list: " + list);

        int index = 0;
        for (String s : list) {
            System.out.println("Index: " + index + ", Element: " + s);
            index++;
        }
    }
}
```
