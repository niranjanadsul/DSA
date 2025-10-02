package SlidingWindow.TwoPointer;

import java.util.Arrays;

public class SubseqWithSumCondition_1 {
    //https://leetcode.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/submissions/1623031018/
    public int numSubseq(int[] nums, int target) {
        int mod=1000000007;
        int[] power = new int[nums.length];
        power[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            power[i] = (power[i - 1] * 2) % mod;
        }

        Arrays.sort(nums);
        int count = 0;
        int start = 0, end = nums.length - 1;
        while (start <= end) {
            if (nums[start] + nums[end] > target) {
                end--;
            } else {
                count = (count + power[end - start]) % mod;
                start++;
            }
        }
        return (int) count;
    }

    public static void main(String[] args) {
        SubseqWithSumCondition_1 subseqWithSumCondition1 = new SubseqWithSumCondition_1();
        int count= subseqWithSumCondition1.numSubseq(new int[]{3,5,6,7},9);//4
        System.out.println(count);
        count = subseqWithSumCondition1.numSubseq(new int[]{3,3,6,8},10);//6
        System.out.println(count);
        count = subseqWithSumCondition1.numSubseq(new int[]{2,3,3,4,6,7},12);//61
        System.out.println(count);
        count=subseqWithSumCondition1.numSubseq(new int[]{7,10,7,3,7,5,4},12);//56
        System.out.println(count);
        count=subseqWithSumCondition1.numSubseq(new int[]{14,4,6,6,20,8,5,6,8,12,6,10,14,9,17,16,9,7,14,11,14,15,13,11,10,18,13,17,17,14,17,7,9,5,10,13,8,5,18,20,7,5,5,15,19,14},22);
        System.out.println(count);
    }
}
