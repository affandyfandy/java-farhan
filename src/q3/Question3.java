package q3;

import java.util.ArrayList;
import java.util.List;

public class Question3 {
    public static List<Integer> getSecondBiggestIndices(int[] arr) {
        if (arr == null || arr.length < 2) {
            throw new IllegalArgumentException("Array must contain at least two elements.");
        }

        int max = Integer.MIN_VALUE;
        // System.out.println("first nax " + max);
        int secondMax = Integer.MIN_VALUE;
        // System.out.println("first secondmax " + secondMax);
        List<Integer> secondMaxIndices = new ArrayList<>();

        // First pass to find the max value
        for (int num : arr) {
            // System.out.println(num);
            if (num > max) {
                max = num;
                // System.out.println(max);
            }
        }

        // Second pass to find the second max value and collect its indices
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            // System.out.println("index" + i + " value " + num);
            // System.out.println("num " + num);
            if (num > secondMax && num < max) {
                secondMax = num;
                // System.out.println("num max " + secondMax);
                // will be clear first
                secondMaxIndices.clear();
                // System.out.println("num max 2 " + secondMax);
                // and then add value of i
                secondMaxIndices.add(i);
                // System.out.println("num max 3 " + secondMax);
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
