package Assignment5;
/*
 * https://medium.com/@reetesh043/the-copyonwritearraylist-internals-a-deep-dive-ff3ebad87697#:~:text=CopyOnWriteArrayList%20provides%20a%20thread%2Dsafe,consistency%20and%20isolation%20for%20readers.
 */

// public class Q6 {
//     public static void main(String[] args) {
//         // creating a CopyOnWriteArrayList
//         CopyOnWriteArrayList<Integer> arrayList = new CopyOnWriteArrayList<>();

//         // Adding elements to the list
//         arrayList.add(1);
//         arrayList.add(2);
//         arrayList.add(3);
//         System.out.println("Size of arrayList after additions: " + arrayList.size());

//         // Display the contents of the list
//         System.out.println("Contents of arrayList: " + arrayList);

//         // Iterate over the list and modify it
//         for (Integer number : arrayList) {
//             System.out.println("Current Element: " + number);
//             arrayList.add(number + 5); // Modify the list by adding 10 to each element
//         }
//         // add new data into jago
//         arrayList.add(40);

//         // Print modified list
//         System.out.println("Modified List: " + arrayList);
//     }
// }
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Q6 {
    public static void main(String[] args) {
        // ArrayList Example
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);

        try {
            // Iterating and modifying an ArrayList using an Iterator
            Iterator<Integer> arrayListIterator = arrayList.iterator();
            while (arrayListIterator.hasNext()) {
                Integer num = arrayListIterator.next();
                if (num == 2) {
                    // This will throw a ConcurrentModificationException in ArrayList
                    arrayList.add(4); // Concurrent modification exception!                
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("Caught ConcurrentModificationException in ArrayList!");
        }

        // List all data in arrayList after the attempted modification
        System.out.println("Contents of arrayList after modification attempt:");
        for (Integer num : arrayList) {
            System.out.print(num + " ");
        }
        System.out.println();

        // CopyOnWriteArrayList Example
        List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add(1);
        copyOnWriteArrayList.add(2);
        copyOnWriteArrayList.add(3);

        // Iterating and modifying a CopyOnWriteArrayList using an Iterator
        Iterator<Integer> copyOnWriteArrayListIterator = copyOnWriteArrayList.iterator();
        while (copyOnWriteArrayListIterator.hasNext()) {
            Integer num = copyOnWriteArrayListIterator.next();
            if (num == 2) {
                // This modification is allowed in CopyOnWriteArrayList
                copyOnWriteArrayList.add(4); // No ConcurrentModificationException!
            }
        }

        // List all data in copyOnWriteArrayList after the modification
        System.out.println("Contents of copyOnWriteArrayList after modification:");
        for (Integer num : copyOnWriteArrayList) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
