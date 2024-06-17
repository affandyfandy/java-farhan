package q3.oneloop;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private static Integer findMax(int[] arr) {
        Integer max = arr[0];
        for (Integer num : arr) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static List<Integer> getSecondBiggestIndices(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        // Find the maximum value in the array
        int max = findMax(arr);
        int secondMax = Integer.MIN_VALUE;
        List<Integer> secondMaxIndices = new ArrayList<>();

        // Iterate to find the second max value and collect its indices
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (num > secondMax && num < max) {
                secondMax = num;
                secondMaxIndices.clear();
                secondMaxIndices.add(i);
            } else if (num == secondMax) {
                secondMaxIndices.add(i);
            }
        }

        return secondMaxIndices;
    }

    public static void main(String[] args) {
        int[] input = { 1, 4, 3, -6, 5, 4 };
        List<Integer> result = getSecondBiggestIndices(input);
        System.out.println(result);
    }
}
