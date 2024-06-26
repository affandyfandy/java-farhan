interface Animal {
    default String eating() {
        return "Nyam nyam....";
    }
}

interface Plant {
    default String eating() {
        return "Nyam nyam Plant....";
    }
}

public class Q1 implements Animal, Plant {
    // Override the eating() method to resolve the conflict
    @Override
    public String eating() {
        // Here we choose to use the eating() method from Animal interface
        // return Plant.super.eating(); this for plant interface
        // return Animal.super.eating(); this for plant interface
        return Animal.super.eating();
    }

    public static void main(String[] args) {
        Q1 n1 = new Q1();
        System.out.println(n1.eating()); // Output: Nyam nyam....
    }
}

// If have the same of default methods from 2 interface , will be error
// Duplicate default methods named eating with the parameters () and () are
// inherited from the types Plant and Animal
// This is a compile-time error. Cannot have two implementation from two
// interfaces.

// if do with default ,The class can still access the default method
// rule is that the class implementing the duplicate default methods 'must'
// override the implementation..