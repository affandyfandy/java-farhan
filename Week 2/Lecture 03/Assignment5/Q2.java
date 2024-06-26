package Assignment5;

import java.util.ArrayList;

public class Q2 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<String>();// Creating arraylist
        list.add("Mango");// Adding object in arraylist
        list.add("Apple");
        list.add("Banana");
        list.add("Grapes");
        // Printing the arraylist object
        System.out.println(list);
        // for (String s1 : list) {
        // if (s1.equals("Apple")) {
        // s1.remove(s1)
        // }
        // }
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals("Apple")) { // Check if current element is "Apple"
                list.remove(i); // Remove "Apple" from the list
                i--; // Decrement i to adjust for the removed element
            }
        }
        System.out.println(list);
        // Printing specifies index
        /*
         * index [0] = mango
         * index [1] = apple
         * index [2] = banana
         * index [3] = grapes
         */
        System.out.println("Returning element: " + list.get(1));

        int index = 0;
        for (String s1 : list) {
            System.out.println("Index: " + index + ", Element: " + s1);
            index++;
        }

    }
}