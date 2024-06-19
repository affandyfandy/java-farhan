package Assignment5;

import java.util.concurrent.CopyOnWriteArrayList;

public class Q6 {
    public static void main(String[] args) {
        // creating a CopyOnWriteArrayList
        CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();

        // Adding elements to the list
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        System.out.println("Size of arrayList after additions: " + arrayList.size());

        // Display the contents of the list
        System.out.println("Contents of arrayList: " + arrayList);

        // Iterate over the list and modify it
        for (Integer number : arrayList) {
            System.out.println("Current Element: " + number);
            arrayList.add(number + 5); // Modify the list by adding 10 to each element
        }
        // add new data into jago
        arrayList.add(40);

        // Print modified list
        System.out.println("Modified List: " + arrayList);
    }
}
