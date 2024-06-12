// public class Main {
//     public static void main(String[] args) {
//         int[] arr = {1, 2, 3};
//         int[] arr2 = arr;
//         System.out.println("Before calling modifyArray: " + java.util.Arrays.toString(arr));
//         modifyArray(arr);
//         System.out.println("After calling modifyArray: " + java.util.Arrays.toString(arr));
//         System.out.println("After calling modifyArray: " + java.util.Arrays.toString(arr2));
//     }

//     public static void modifyArray(int[] arr) {
//         arr[0] = 100;
//         System.out.println("Inside modifyArray: " + java.util.Arrays.toString(arr));
//     }
// }

public class Main {
    public static void main(String[] args) {
        int num = 10;
        int num2 = num;
        System.out.println("Before calling modifyValue: " + num);
        modifyValue(num);
        System.out.println("After calling modifyValue: " + num);
        System.out.println("After calling modifyValue: " + num2);
    }

    public static void modifyValue(int x) {
        x = 20;
        System.out.println("Inside modifyValue: " + x);
    }
}