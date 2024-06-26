<!-- Explain previous slice as slice 6 (stack & heap)
.
It’s better if you have another String field in StudentClass -->

# **Stack & Heap**

In java memory allocation divided into **Stack Memory** and **Heap Memory**,Stack memory is used for storing method call frames and local variables, with each thread having its own stack. Heap memory, on the other hand, is where objects are allocated, particularly those created using the new keyword.

## **Stack Memory**

Stack memory is contains primitive data type like int , float , double and others.
Access to this memory is in Last-In-First-Out (LIFO) order
Whenever we call a new method, a new block is created on top of the stack which contains values specific to that method, like primitive variables and references to objects.

## **Heap Memory**

Heap memory is used for the dynamic memory allocation of Java objects and JRE classes at runtime, It means the data can be add , update and delete in runtime.
Stack memory is contains like array data, list data and others

## Characteristic
### Stack Memory
- LIFO (Last In First Out): Stack memory operates on a LIFO principle.
- Scope: It stores short-lived variables and references.
- Size: Stack memory is smaller and more limited in size compared to heap memory.
- Automatic Memory Management: Stack memory is automatically managed by the JVM, meaning it is freed when the method call ends.
- Initial State: The reference 'obj' is created in the stack, pointing to the object in the heap.

### Heap Memory
- Dynamic Memory Allocation: Heap memory is used for dynamic memory allocation.
- Size: Heap memory is larger and can grow as needed (within the limits set by the JVM).
- Garbage Collection: Memory in the heap is managed through garbage collection, which reclaims memory used by objects that are no longer referenced.
- Initial State: A new instance of 'class' is created for 'obj'.

## **Modify the object that the reference points to**

---

### **Updated MyClass become StudentClass**

```java
class StudentClass {
    String name;
    double gpa;
}
```

### **CODE 1**

```java
package q6;

class StudentClass {
    String name;
    double gpa;
}

public class Question {
    public static void main(String[] args) {
        StudentClass s1 = new StudentClass();
        s1.gpa = 3.70;
        s1.name = "Saka";

        modifyObject(s1);
        System.out.println("obj.value after modifyObject: " + s1.name + ", " + s1.gpa);
    }

    public static void modifyObject(StudentClass x) {
        x.name = "Yusuf";
        x.gpa = 3.90;

    }

}
```

**Explaination**

```java
StudentClass s1 = new StudentClass();
s1.name = "Putra";
s1.gpa = 3.70;
```

In stack, method main is added, obj reference is created and points to a new StudentClass object on the heap, but the value and name is still null. and then after created instance class is updated to Putra and 3.70

```java
modifyObject(s1);
}

public static void modifyObject(StudentClass x) {
    x.name = "Yusuf";
    x.gpa = 3.90;
}
```

The obj reference is passed to the modifyObject(). The parameter that is x in modifyObject() holds the same reference as obj, pointing to the same StudentClass instance on the heap.
The obj refernce is passed as parameter in modifyObject. The parameter in modifyObject holds the same reference as obj, point to the same StudentClass

```java
System.out.println("obj.value after modifyObject: " + s1.name + ", " + s1.gpa);
```

Prints the updated value of the instance, and the output will be "obj.value after modifyObject: Yusuf, 3.70"

## **Cannot change the reference itself to point to a different object**

---

### **CODE 2**

```java
package q6;

class StudentClass {
    String name;
    double gpa;
}

public class Question {
    public static void main(String[] args) {
        StudentClass s2 = new StudentClass();
        s2.name = "Yusuf";
        s2.gpa = 3.70;

        changeReferenceObject(s2);
        System.out.println("obj.value after changepreference: " + s2.name + ", " + s2.gpa);
    }
    public static void changeReferenceObject(StudentClass x) {
        x = new StudentClass();
        x.name = "Putra";
        x.gpa = 3.9;
    }

}

```

**Explaination**

```java
StudentClass s2 = new StudentClass();
s2.name = "Yusuf";
s2.gpa = 3.70;
```

In stack, method main is added, obj reference is created and points to a new StudentClass object on the heap, but the value and name is still null. and then after created instance class is updated to Yusuf and 3.70

```java
changeReferenceObject(s2);

public static void changeReferenceObject(StudentClass x) {
    x = new StudentClass();
    x.name = "Putra";
    x.gpa = 3.9;
}
```

The obj reference is passed to the changeReferenceObject(). The parameter that is x in changeReferenceObject() holds the same reference as obj and pointing to the same instance on the heap..
The changeReferenceObject() creates a new instance of StudentClass on the heap in parameter x. Now x points to the new StudentClass
The new object will have different name and gpa.s2 have attr name is Putra and gpa is 3.9

```java
System.out.println("obj.value after changereference: " + s2.name + ", " + s2.gpa);
```

s2 still reference the first StudentClass object. In heap, the original StudentClass object remains unchanged, while the new object created inside changeReferenceObject() is discarded after the method returns.
It means new referece cannot to be change the original object
So the output will be "obj.value after modifyObject: Yusuf, 3.70"
