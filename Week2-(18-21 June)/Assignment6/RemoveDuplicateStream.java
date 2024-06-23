package Assignment6;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveDuplicateStream {
    public static void main(String[] args) {
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8));
        List<Integer> p = Arrays.asList( 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8);
        System.out.println(p);

        System.out.println("Before remove duplicate " + numbersList); // output : [1, 1, 2, 3, 3, 3, 4, 5, 6, 6, 6, 7, 8]

        List<Integer> listWithoutDuplicates = numbersList.stream().distinct().collect(Collectors.toList());

        System.out.println("After remove duplicate " + listWithoutDuplicates); // output : [1, 2, 3, 4, 5, 6, 7, 8]
    }
}
