package Stack_Queue.MonotonicStack_10;

import java.util.Arrays;
import java.util.Stack;

public class SumOfSubArrayMinimums_6 {
    //https://leetcode.com/problems/sum-of-subarray-minimums/description/
    /*Given an array of integers arr, find the sum of min(b),
    where b ranges over every (contiguous) subarray of arr.
    Since the answer may be large, return the answer modulo 109 + 7.
    Example 1:

    Input: arr = [3,1,2,4]
    Output: 17
    Explanation:
    Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4].
    Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
    Sum is 17.
    Example 2:

    Input: arr = [11,81,94,43,3]
    Output: 444*/
    static final int MOD = 1_000_000_007;

    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        long ans = 0;

        int[] left = new int[n];
        int[] right = new int[n];

        Stack<Integer> stack = new Stack<>();

        // Compute left[i] = distance to previous smaller
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                stack.pop();
            }
            //stack empty indicates there is no previous smaller so distance is from start element
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }

        stack.clear();

        // Compute right[i] = distance to next smaller or equal
        //same loop set up but from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }
            //empty stack indicates thee is no next smaller element so distance up to end os array
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        // Contribution of each arr[i]
        for (int i = 0; i < n; i++) {
            long contrib = (long) arr[i] * left[i] * right[i];
            ans = (ans + contrib) % MOD;
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        SumOfSubArrayMinimums_6 sumOfSubArrayMinimums6=new SumOfSubArrayMinimums_6();
        System.out.println(sumOfSubArrayMinimums6.sumSubarrayMins(new int[]{3,1,2,4}));//17
        System.out.println(sumOfSubArrayMinimums6.sumSubarrayMins(new int[]{11,81,94,43,3}));//444
    }
}
