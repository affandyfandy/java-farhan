# **How oop principles perform in java?**

## **Overview Example Code**

The code consists of the following components:

### **Shape class**

An abstract class representing a general shape. It encapsulates common properties of shapes such as color and filled status, and provides abstract methods to calculate the area and perimeter of a shape.

Circle class: A subclass of Shape representing a circle. It includes methods to calculate the area and perimeter of a circle because Circle is subclass from shape (inheritence).

### **Rectangle class:**

A subclass of Shape representing a rectangle. It includes methods to calculate the area and perimeter of a rectangle because rectangle is subclass from shape (inheritence)..

### **Main class:**

The main class where instances of circles and rectangles are created and their properties are tested.

### **Encapsulation**

Encapsulation is the practice of bundling related data into a structured unit, along with the methods used to work with that data
Encapsulation is the process of wrapping data and related functions into a single unit (object). Encapsulation limits access to object data and methods, preventing their misuse and ensuring their proper functioning.

```java
private String color;
private boolean filled;

public String getColor() {
    return color;
}
```
### **Polymorphism**

Polymorphism is describes situations in which something occurs in several different forms. Polymorphism is also the ability of an object to take on multiple forms. This allows objects of different classes to be used interchangeably, as long as they implement a certain interface (have methods of the same name).

```java
Shape circle = new Circle(5.0, "Red", true);
Shape rectangle = new Rectangle(4.0, 6.0, "Blue", false);
```

### **Inheritence**

Inheritance is the ability to create a new class (child class) from an existing one (parent class). The child class typically inherits the attributes (members and methods) of
the parent class, although it can also redefine them.

```java
// Subclass representing a Circle
class Circle extends Shape {
    private double radius;

    // Constructor for Circle
    public Circle(double radius, String color, boolean  filled) {
        super(color, filled);
        this.radius = radius;
    }
}
```

### **Abstraction**

Abstraction is the process of hiding unnecessary details of an object’s internal structure. By abstracting an object’s data, its structure and behavior can be kept separate and more easily understood.

```java
// Abstract class to represent a general shape
abstract class Shape {
    private String color;
    private boolean filled;

    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    public abstract double calculateArea();
}
```
