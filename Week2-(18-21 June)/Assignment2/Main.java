// File: Main.java

// Definisi Functional Interface
@FunctionalInterface
interface MyFunctionalInterface {
    void execute();

    // Default method (bukan metode abstrak)
    default void defaultMethod() {
        System.out.println("This is a default method in MyFunctionalInterface");
    }

    // Static method (bukan metode abstrak)
    static void staticMethod() {
        System.out.println("This is a static method in MyFunctionalInterface");
    }
}

// Definisi Interface Biasa
interface MyRegularInterface {
    void method1();

    void method2();

    // Default method
    default void defaultMethod() {
        System.out.println("This is a default method in MyRegularInterface");
    }

    // Static method
    static void staticMethod() {
        System.out.println("This is a static method in MyRegularInterface");
    }
}

// Implementasi Interface Biasa
class MyClass implements MyRegularInterface {
    @Override
    public void method1() {
        System.out.println("Method1 implementation");
    }

    @Override
    public void method2() {
        System.out.println("Method2 implementation");
    }
}

public class Main {
    public static void main(String[] args) {
        // Menggunakan Functional Interface dengan Lambda Expression
        MyFunctionalInterface myFunc = () -> System.out.println("Executing!");
        myFunc.execute();

        // Menggunakan default method dari Functional Interface
        myFunc.defaultMethod();

        // Menggunakan static method dari Functional Interface
        MyFunctionalInterface.staticMethod();

        // Menggunakan Interface Biasa
        MyRegularInterface myClass = new MyClass();

        // Memanggil metode yang diimplementasikan
        myClass.method1();
        myClass.method2();

        // Memanggil default method dari Interface Biasa
        myClass.defaultMethod();

        // Memanggil static method dari Interface Biasa
        MyRegularInterface.staticMethod();
    }
}
