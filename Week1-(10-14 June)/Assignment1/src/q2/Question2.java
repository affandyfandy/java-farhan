package q2;

// Abstract class to represent a general shape
abstract class Shape {
    // Encapsulated fields for common properties of shapes
    private String color;
    private boolean filled;

    // Constructor for Shape
    public Shape(String color, boolean filled) {
        this.color = color;
        this.filled = filled;
    }

    // Abstract method to calculate area, to be implemented by subclasses
    public abstract double calculateArea();

    // Abstract method to calculate perimeter, to be implemented by subclasses
    public abstract double calculatePerimeter();

    // Getter for color
    public String getColor() {
        return color;
    }

    // Setter for color
    public void setColor(String color) {
        this.color = color;
    }

    // Getter for filled
    public boolean isFilled() {
        return filled;
    }

    // Setter for filled
    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    // Polymorphic method to provide string representation of the shape
    @Override
    public String toString() {
        return "Shape[color=" + color + ", filled=" + filled + "]";
    }
}

// Subclass representing a Circle
class Circle extends Shape {
    private double radius;

    // Constructor for Circle
    public Circle(double radius, String color, boolean filled) {
        super(color, filled);
        this.radius = radius;
    }

    // Override calculateArea method for Circle
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    // Override calculatePerimeter method for Circle
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }

    // Polymorphic method to provide string representation of Circle
    @Override
    public String toString() {
        return "Circle[radius=" + radius + "," + super.toString() + "]";
    }
}

// Subclass representing a Rectangle
class Rectangle extends Shape {
    private double width;
    private double length;

    // Constructor for Rectangle
    public Rectangle(double width, double length, String color, boolean filled) {
        super(color, filled);
        this.width = width;
        this.length = length;
    }

    // Override calculateArea method for Rectangle
    @Override
    public double calculateArea() {
        return width * length;
    }

    // Override calculatePerimeter method for Rectangle
    @Override
    public double calculatePerimeter() {
        return 2 * (width + length);
    }

    public double calculatePerimeter(int rec) {
        return 2 * (width + length) / rec;
    }

    // Polymorphic method to provide string representation of Rectangle
    @Override
    public String toString() {
        return "Rectangle[width=" + width + ",length=" + length + "," + super.toString() + "]";
    }
}

// Main class
public class Question2 {
    public static void main(String[] args) {
        // Creating instances of shapes
        // Polymorphism
        Shape circle = new Circle(5.0, "Red", true);
        Shape rectangle = new Rectangle(4.0, 6.0, "Blue", false);

        // Testing polymorphic behavior
        System.out.println(circle); // Output: Circle[radius=5.0,Shape[color=Red, filled=true]]
        System.out.println("Area of Circle: " + circle.calculateArea()); // Output: 78.53981633974483
        System.out.println("Perimeter of Circle: " + circle.calculatePerimeter()); // Output: 31.41592653589793
        System.out.println("Perimeter of Circle: " + circle.calculatePerimeter()); // Output: 31.41592653589793

        System.out.println(rectangle); // Output: Rectangle[width=4.0,length=6.0,Shape[color=Blue, filled=false]]
        System.out.println("Area of Rectangle: " + rectangle.calculateArea()); // Output: 24.0
        System.out.println("Perimeter of Rectangle: " + rectangle.calculatePerimeter()); // Output: 20.0

        // need cast for call method overloading superclass
        if (rectangle instanceof Rectangle) {
            Rectangle rect = (Rectangle) rectangle;
            System.out.println("Perimeter Overloading of Rectangle: " + rect.calculatePerimeter(2));
        }
    }
}
