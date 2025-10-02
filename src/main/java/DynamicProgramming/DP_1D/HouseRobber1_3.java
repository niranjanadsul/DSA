package DynamicProgramming.DP_1D;

import java.util.Arrays;

public class HouseRobber1_3 {
    //https://leetcode.com/problems/house-robber/
    /*You are a professional robber planning to rob houses along a street.
    Each house has a certain amount of money stashed, the only constraint stopping you from robbing
    each of them is that adjacent houses have security systems connected and it will automatically
    contact the police if two adjacent houses were broken into on the same night.
    Given an integer array nums representing the amount of money of each house,
    return the maximum amount of money you can rob tonight without alerting the police.*/

    //can use take not take approach
    // Along with DP
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,-1);
        return robHouse(nums.length-1,nums,dp);
    }

    public int robHouse(int index, int[] nums, int[] dp){
        if(index<0)
            return 0;
        if(dp[index]!=-1)
            return dp[index];
        //take
        int take = nums[index]+robHouse(index-2,nums,dp);
        int notTake = robHouse(index-1,nums,dp);
        return dp[index]=Math.max(take,notTake);
    }

    public static void main(String[] args) {
        HouseRobber1_3 houseRobber13=new HouseRobber1_3();
        int max = houseRobber13.rob(new int[]{1,2,3,1});//4
        System.out.println(max);
        max = houseRobber13.rob(new int[]{2,7,9,3,1});//12
        System.out.println(max);
    }
}
