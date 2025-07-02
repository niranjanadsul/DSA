package DynamicProgramming.DP_1D;

import java.util.Arrays;

public class HouseRobber2_4 {
    //the houses are arranged in a circle  and last and first house are adjacent
    public int rob(int[] nums) {
        if(nums.length==1)
            return nums[0];
        int[] dp = new int[nums.length-1];
        Arrays.fill(dp,-1);
        int[] num1=Arrays.copyOfRange(nums,0,nums.length-1);
        int max1 = robHouse(num1.length-1,num1,dp);
        int[] num2=Arrays.copyOfRange(nums,1,nums.length);
        Arrays.fill(dp,-1);
        int max2 = robHouse(num2.length-1,num2,dp);
        return Math.max(max1,max2);
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
        HouseRobber2_4 houseRobber24 = new HouseRobber2_4();
        int max = houseRobber24.rob(new int[]{2,3,2});//3
        System.out.println(max);
        max = houseRobber24.rob(new int[]{1,2,3,1});//4
        System.out.println(max);
    }
}
