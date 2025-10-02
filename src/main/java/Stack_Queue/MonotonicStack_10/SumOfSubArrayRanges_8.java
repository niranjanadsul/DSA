package Stack_Queue.MonotonicStack_10;

import java.util.Stack;

public class SumOfSubArrayRanges_8 {
    //https://leetcode.com/problems/sum-of-subarray-ranges/description/
    /*You are given an integer array nums.
    The range of a subarray of nums is the difference between the largest and
    smallest element in the subarray.
    Return the sum of all subarray ranges of nums.
    A subarray is a contiguous non-empty sequence of elements within an array.

    Example 1:

    Input: nums = [1,2,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
    [1], range = largest - smallest = 1 - 1 = 0
    [2], range = 2 - 2 = 0
    [3], range = 3 - 3 = 0
    [1,2], range = 2 - 1 = 1
    [2,3], range = 3 - 2 = 1
    [1,2,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.
    Example 2:

    Input: nums = [1,3,3]
    Output: 4
    Explanation: The 6 subarrays of nums are the following:
    [1], range = largest - smallest = 1 - 1 = 0
    [3], range = 3 - 3 = 0
    [3], range = 3 - 3 = 0
    [1,3], range = 3 - 1 = 2
    [3,3], range = 3 - 3 = 0
    [1,3,3], range = 3 - 1 = 2
    So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
    Example 3:

    Input: nums = [4,-2,-3,4,1]
    Output: 59
    Explanation: The sum of all subarray ranges of nums is 59.*/
    public long subArrayRanges(int[] arr) {
        int n = arr.length;

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

        long sumOfSubArrayMins = 0;
        // Contribution of each arr[i]
        for (int i = 0; i < n; i++) {
            long contrib = (long) arr[i] * left[i] * right[i];
            sumOfSubArrayMins = (sumOfSubArrayMins + contrib);// % MOD;
        }

        //let's calculate sum of subArray maximums
        stack.clear();
        left = new int[n];
        right = new int[n];
        // Compute left[i] = distance to previous greater
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
                stack.pop();
            }
            //stack empty indicates there is no previous greater so distance is from start element
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        stack.clear();

        // Compute right[i] = distance to next greater or equal
        //same loop set up but from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] <= arr[i]) {
                stack.pop();
            }
            //empty stack indicates thee is no next greater element so distance up to end os array
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }

        long sumOfSubArrayMaximums = 0;
        // Contribution of each arr[i]
        for (int i = 0; i < n; i++) {
            long contrib = (long) arr[i] * left[i] * right[i];
            sumOfSubArrayMaximums = (sumOfSubArrayMaximums + contrib);// % MOD;
        }

        return sumOfSubArrayMaximums-sumOfSubArrayMins;
    }

    public static void main(String[] args) {
        SumOfSubArrayRanges_8 sumOfSubArrayRanges8=new SumOfSubArrayRanges_8();
        System.out.println(sumOfSubArrayRanges8.subArrayRanges(new int[]{1,2,3}));//4
        System.out.println(sumOfSubArrayRanges8.subArrayRanges(new int[]{1,3,3}));//4
        System.out.println(sumOfSubArrayRanges8.subArrayRanges(new int[]{4,-2,-3,4,1}));//59
    }
}
