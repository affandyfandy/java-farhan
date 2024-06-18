# **Primitive Types vs. Reference Objects in Java**

In Java, data types can be broadly classified into two categories: primitive types and reference types. Understanding the differences between them is crucial for Java developers to write efficient and bug-free code.

## **Primitive Types**

Primitive types represent single values and are predefined by the language. They are directly supported by the Java programming language and are not objects. Java has eight primitive data types:

- `byte`: 8-bit signed integer.
- `short`: 16-bit signed integer.
- `int`: 32-bit signed integer.
- `long`: 64-bit signed integer.
- `float`: 32-bit floating-point.
- `double`: 64-bit floating-point.
- `char`: 16-bit Unicode character.
- `boolean`: Represents true or false.

## **Characteristics of Primitive Types:**

Stored Directly: Primitive type variables store the actual value.
Memory Allocation: Each variable of a primitive type holds its value directly in memory.
Immutable: Primitive values cannot be modified once assigned.
Copied by Value: When assigned to another variable or passed as a method argument, the actual value is copied.

## **Int**
```java
public class Main {
    public static void main(String[] args) {
        int num = 10;
        System.out.println("Before calling modifyValue: " + num);
        modifyValue(num);
        System.out.println("After calling modifyValue: " + num);
    }

    public static void modifyValue(int x) {
        x = 20;
        System.out.println("Inside modifyValue: " + x);
    }
}
```
## **Char**

```java
package q1;

public class Question {
    public void primitiveChar(char letter1, char letter2) {
        System.out.println("Primitive Data Type Char:");
        System.out.println("Primitive1 Value: " + letter1);
        System.out.println("Primitive2 value: " + letter2);
        letter1 = letter2;
        letter2 = 'A';
        System.out.println("After change:");
        System.out.println("Primitive1 Value: " + letter1);
        System.out.println("Primitive2 value: " + letter2);
    }
}

import q1.Question;
public class App {
    public static void main(String[] args) throws Exception {
        Question question = new Question();

        char letter1 = 'A';
        char letter2 = 'A';
        question.primitiveChar(letter1, letter2);
    }
}

```

## **Reference Types**

Reference types store references (or addresses) to objects in memory. Objects are instances of classes, and they can be manipulated using methods defined by their class. Reference types include classes, arrays, interfaces, and enumerations.

## **Characteristics of Reference Types:**

Stored by Reference: Reference type variables store references to objects in memory.
Memory Allocation: Objects are created dynamically in the heap memory, and reference variables hold the memory address of the object.
Mutable: Objects can be modified through their methods.
Copied by Reference: When assigned to another variable or passed as a method argument, the reference (memory address) is copied, not the object itself.

## **Array Int**
```java
public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3};
        System.out.println("Before calling modifyArray: " + java.util.Arrays.toString(arr));
        modifyArray(arr);
        System.out.println("After calling modifyArray: " + java.util.Arrays.toString(arr));
    }

    public static void modifyArray(int[] arr) {
        arr[0] = 100;
        System.out.println("Inside modifyArray: " + java.util.Arrays.toString(arr));
    }
}
```

## **String**
```java
public class Main {
    public static void main(String[] args) {
        String str = "Hello";
        System.out.println("Before calling modifyString: " + str);
        modifyString(str);
        System.out.println("After calling modifyString: " + str);
    }

    public static void modifyString(String s) {
        s += " World";
        System.out.println("Inside modifyString: " + s);
    }
}

```

```java
package q1;

public class Question {
  public void referenceString(String string1, String string2) {
        System.out.println("\nReferences Object String:");
        System.out.println("String Value1: " + string1);
        System.out.println("String value2: " + string2);
        string2 += " World";
        System.out.println("After Change:");
        System.out.println("String Value1: " + string1);
        System.out.println("String value2: " + string2);
    }
}


public class App {
    public static void main(String[] args) throws Exception {
        Question question = new Question();

        String string1 = "Hello";
        String string2 = string1;
        question.referenceString(string1, string2);
    }
}

```

So value types consist like int, char, bool,double, and value types store the actual data values.
Reference types consist of classes, and arrays , like list ,int[], String and others
So in value types and Classes and arrays are reference types. and referece types stor reference to objects.