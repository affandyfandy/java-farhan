package Assignment7;

import Assignment7.models.Person;
import Assignment7.utils.DataUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ListOperation {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person(1, "Alice", 30),
                new Person(2, "Bob", 25),
                new Person(3, "Charlie", 35),
                new Person(4, "Alice", 30),
                new Person(5, "Dave", 40));

        // // Remove duplicates by name
        List<Person> uniqueByName = DataUtils.removeDuplicatesByField(people, Person::getName);
        System.out.println("Remove Duplicate by Name");
        for (Person p : uniqueByName) {
            System.out.println(p);
        }
        /*
         * Output
         * Remove Duplicate by Name
         * Person{id=1, name='Alice', age=30}
         * Person{id=2, name='Bob', age=25}
         * Person{id=3, name='Charlie', age=35}
         * Person{id=5, name='Dave', age=40}
         */

        // Sort by age
        List<Person> sortedByAge = DataUtils.sortByField(people, Person::getAge);
        System.out.println("Sorted by age: ");
        for (Person p : sortedByAge) {
            System.out.println(p);
        }
        /*
         * Output
         * Sorted by age:
         * Person{id=2, name='Bob', age=25}
         * Person{id=1, name='Alice', age=30}
         * Person{id=4, name='Alice', age=30}
         * Person{id=3, name='Charlie', age=35}
         * Person{id=5, name='Dave', age=40}
         */

        // Find the person with the maximum age
        Optional<Person> maxAgePerson = DataUtils.findMaxByField(people,
                Person::getAge);
        maxAgePerson.ifPresent(person -> System.out.println("Oldest Person: " +
                person));
        /*
         * Output
         * Oldest Person: Person{id=5, name='Dave', age=40}
         */
    }
}