package Assignment7;

import java.util.*;
import Assignment7.utils.*;

public class RemoveDuplicateGenerics {
    public static void main(String[] args) {
        // Using an ArrayList (which implements Collection)
        List<String> list = new ArrayList<>(Arrays.asList("apple", "banana", "apple", "orange"));
        List<String> uniqueList = DataUtils.removeDuplicates(list);
        System.out.println("Unique List: " + uniqueList);

        // Using a HashSet (which implements Collection)
        Set<String> set = new HashSet<>(Arrays.asList("apple", "banana", "apple", "orange"));
        List<String> uniqueSetList = DataUtils.removeDuplicates(set);
        System.out.println("Unique Set List: " + uniqueSetList);

        // Using an ArrayList of integers
        List<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 1, 2, 4, 5));
        List<Integer> uniqueIntList = DataUtils.removeDuplicates(intList);
        System.out.println("Unique Integer List: " + uniqueIntList);
    }
}
