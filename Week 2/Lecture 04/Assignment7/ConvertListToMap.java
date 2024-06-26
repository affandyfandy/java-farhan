package Assignment7;

import Assignment7.models.Person;
import Assignment7.utils.DataUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class ConvertListToMap {
    public static void main(String[] args) {
        List<Person> peoples = Arrays.asList(
                new Person(1, "Alice", 30),
                new Person(2, "Bob", 25),
                new Person(3, "Charlie", 35),
                new Person(4, "Alice", 30),
                new Person(5, "Dave", 40));

        // before convert
        for (Person p : peoples) {
            System.out.println("People " + p.getName());
        }

        // Convert list of peoples to map using name as the key
        Map<String, Person> pmap = DataUtils.convertListToMap(peoples, Person::getName);
        System.out.println("Person map by name " + pmap);
        /*
         * output :
         * Person map by name {Bob=Person{id=2, name='Bob', age=25}, Alice=Person{id=4,
         * name='Alice', age=30}, Charlie=Person{id=3, name='Charlie', age=35},
         * Dave=Person{id=5, name='Dave', age=40}}
         */
        System.out.println("Iterating over the people map:");
        pmap.forEach((isbn, people) -> System.out.println("People: " + isbn + ", People: " + people));

    }
}
