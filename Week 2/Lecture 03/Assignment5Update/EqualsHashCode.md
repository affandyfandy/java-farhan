# 🟰 Equal and Hashcode

## 📝 Definition

---

The `equals()` method in Java is used to compare two objects for equality. By default, it compares memory addresses to determine if two references point to the same object in memory.

Java Object `hashCode()` is a native method and returns the integer hash code value of the object.

`Object class defined equals() method like this:`

```java
public boolean equals(Object obj) {
        return (this == obj);
}
```

## Importance of equals() and hashCode() method

---

hashCode() and equals() method are used in Hash table based implementations in java for storing and retrieving data.

- If o1.equals(o2), then o1.hashCode() == o2.hashCode() should always be true.

- If o1.hashCode() == o2.hashCode is true, it doesn’t mean that o1.equals(o2) will be true.

## 🪧 Contract

---

### 🖋️ .equals() Contract

- reflexive: an object must equal itself
  symmetric: x.equals(y) must return the same result as y.equals(x)
  - x.equals(y) == y.equals(x)
- transitive: if `x.equals(y)` is true and `y.equals(z)` is true, then also `x.equals(z)` is true
- consistent: the value of .equals() should change only if a property that is contained in .equals() changes (no randomness allowed)
- `x.equals(null)` must return false

### 🖋️ .hashcode contract

- internal consistency: the value of hashCode() may only change if a property that is in equals() changes
- equals consistency: objects that are equal to each other must return the same hashCode
  - `x.equals(y) == (x.hashCode() == y.hashCode())`
- collisions: unequal objects may have the same hashCode

## Override of methods

---

To override either .hashcode or equals , dont to violation of the contract by override implementation

`Override equals`

```java
  @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        // Custom equals implementation that considers both 'name' and 'age' fields
        return age == person.age && Objects.equals(name, person.name);
    }
```

`override .hashcde`

```java
@Override
public int hashCode() {
    return Objects.hash(name, age);
}
```

## 🧑‍💻 Example

```java
public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Person person = (Person) obj;
        // Custom equals implementation that considers both 'name' and 'age' fields
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
```
