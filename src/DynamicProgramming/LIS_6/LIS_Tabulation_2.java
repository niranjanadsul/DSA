package DynamicProgramming.LIS_6;

import java.util.Arrays;

public class LIS_Tabulation_2 {
    //https://leetcode.com/problems/longest-increasing-subsequence/
    public int lengthOfLIS(int[] nums) {
        int[][] dp = new int[nums.length][nums.length+1];

        //fill 1st column
        //as the prev index cannot be same or smaller than current index i
        for(int index =0;index<nums.length;index++){
            dp[index][0]=0;
        }
        //fill 1st row
        //we will use the take condition of prev == nums.length
        for(int prev=1;prev<=nums.length;prev++){
            if(prev == nums.length || nums[0]<nums[prev])
                dp[0][prev]=1;
        }
        for(int index =1;index<nums.length;index++){
            for(int prev=1;prev<=nums.length;prev++){
                //take
                int take = Integer.MIN_VALUE;
                //when prev = n means no other number is yet taken
                //and this is the first number
                if(prev==nums.length || nums[index]<nums[prev]){
                    take = 1+dp[index-1][index];
                }
                int notTake = dp[index-1][prev];
                dp[index][prev]=Math.max(take,notTake);
            }
        }
        return dp[nums.length-1][nums.length];
    }
}
