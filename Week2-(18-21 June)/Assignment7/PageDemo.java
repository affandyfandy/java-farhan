package Assignment7;

import Assignment7.models.Page;
import Assignment7.models.Person;
import Assignment7.utils.*;

import java.util.Arrays;
import java.util.List;

public class PageDemo {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person(1, "Alice", 30),
                new Person(2, "Bob", 25),
                new Person(3, "Charlie", 35),
                new Person(4, "Dave", 40),
                new Person(5, "Emma", 28),
                new Person(6, "Frank", 32));

        int pageNumber = 1;
        int size = 3;

        Page<Person> personPage = PageUtils.getPage(people, pageNumber, size);
        System.out.println("Page " + personPage.getPageNumber() + ":");
        personPage.getData().forEach(System.out::println);

        while (personPage.hasNext()) {
            pageNumber++;
            personPage = PageUtils.getPage(people, pageNumber, size);
            System.out.println("Page " + personPage.getPageNumber() + ":");
            personPage.getData().forEach(System.out::println);
        }
    }
}
