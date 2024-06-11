package q4;

public class Question4 {

    public static int[] findZeroSumSubarray(int[] nums) {
        for (int start = 0; start < nums.length; start++) {
            int sum = 0;
            for (int end = start; end < nums.length; end++) {
                sum += nums[end];
                if (sum == 0) {
                    return new int[] { start, end };
                }
            }
        }
        return new int[] { -1, -1 }; // If no zero sum subarray is found
    }

    public static void main(String[] args) {
        int[] nums = { 1, 2, 2, -6, 5, 4, 0 };
        int[] result = findZeroSumSubarray(nums);
        System.out.println("Output: [" + result[0] + ", " + result[1] + "]");
    }
}
