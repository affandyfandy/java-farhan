package Assignment5Update;

import java.util.Objects;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Person person = (Person) obj;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    public static void main(String[] args) {
        Person person1 = new Person("Alice", 30);
        Person person2 = new Person("Alice", 30);
        String person3 = " ";
        String person4 = "Hello";
        System.out.println(person4.equals(person3));
        System.out.println(person4 == person3);
        // Using equals() method
        System.out.println("person1.equals(person2): " + person1.equals(person2)); // true
        if (person1.equals(person2)) {
            System.out.println("Hash codes are equal: " + (person1.hashCode() == person2.hashCode()));
        }

        // Using hashCode() method
        System.out.println("Hash code of person1: " + person1.hashCode());
        System.out.println("Hash code of person2: " + person2.hashCode());
    }
}

// class Person {
//     private String name;
//     private int age;

//     public Person(String name, int age) {
//         this.name = name;
//         this.age = age;
//     }

//     @Override
//     public int hashCode() {
//         // Custom hash code implementation that only considers the 'age' field
//         return Objects.hash(age);
//     }

    // @Override
    // public boolean equals(Object obj) {
    //     if (this == obj) {
    //         return true;
    //     }
    //     if (obj == null || getClass() != obj.getClass()) {
    //         return false;
    //     }
    //     Person person = (Person) obj;
    //     // Custom equals implementation that considers both 'name' and 'age' fields
    //     return age == person.age && Objects.equals(name, person.name);
    // }

//     @Override
//     public String toString() {
//         return "Person{name='" + name + "', age=" + age + "}";
//     }

//     public static void main(String[] args) {
//         Person person1 = new Person("Alice", 25);
//         Person person2 = new Person("Bob", 25);
//         Person person3 = new Person("Alice", 30);
//         Person person4 = new Person("Alice", 31);
//         System.out.println(person1.equals(null));
//         System.out.println("person1.hashCode() == person2.hashCode(): " + (person1.hashCode() == person2.hashCode()));
//         System.out.println("person1.equals(person2): " + person1.equals(person2));

//         System.out.println("person1.hashCode() == person3.hashCode(): " + (person1.hashCode() == person4.hashCode()));
//         System.out.println("person1.equals(person3): " + person1.equals(person4));
//     }
// }
