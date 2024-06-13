package q3;

import java.util.ArrayList;
import java.util.Arrays;

public class Question3 {

    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 4, 3, -6, 5, 4));
        System.out.println(findsecondMaxIndex(numbers)); // Output: [1, 5]
    }

    public static ArrayList<Integer> findsecondMaxIndex(ArrayList<Integer> numbers) {
        int max = numbers.get(0);
        int secondMax = numbers.get(0);
        int temp = 0;

        ArrayList<Integer> secondMaxIndexes = new ArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {

            int number = numbers.get(i);
            if (number > max) {
                secondMax = max;
                max = number;
                temp = numbers.indexOf(secondMax);
                secondMaxIndexes.clear();
            }

            if (number == secondMax) {
                secondMaxIndexes.add(i);
            }
        }

        secondMaxIndexes.add(0, temp);

        return secondMaxIndexes;
    }
}
