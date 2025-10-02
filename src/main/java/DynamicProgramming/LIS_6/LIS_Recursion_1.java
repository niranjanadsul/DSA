package DynamicProgramming.LIS_6;

import java.util.Arrays;

public class LIS_Recursion_1 {
    //https://leetcode.com/problems/longest-increasing-subsequence/
    /*Given an integer array nums, return the length of the longest strictly increasing subsequence.
    Example 1:
    Input: nums = [10,9,2,5,3,7,101,18]
    Output: 4
    Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.*/
    //Pattern:
    //CURRENT element is TAKEN based on PREVIOUS element taken
    //TC = O(N*N)
    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][nums.length+1];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        return lis(nums.length-1,nums.length,dp,nums);
    }

    public int lis(int index, int prev, int[][] dp, int[] nums){
        if(index<0){
            return 0;
        }
        if(dp[index][prev]!=-1)
            return dp[index][prev];
        //take
        int take = Integer.MIN_VALUE;
        //when prev = n means no other number is yet taken
        //and this is the first number
        if(prev==nums.length || nums[index]<nums[prev]){
            take = 1+lis(index-1,index,dp,nums);
        }
        int notTake = lis(index-1,prev,dp,nums);
        return dp[index][prev]=Math.max(take,notTake);
    }
}
