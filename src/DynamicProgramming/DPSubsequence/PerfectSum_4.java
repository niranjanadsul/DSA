package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class PerfectSum_4 {
    //https://www.geeksforgeeks.org/problems/perfect-sum-problem5633/1
    public int perfectSum(int[] nums, int target) {
        // code here
        int sum = 0;
        for(int n:nums)
            sum+=n;
        int[][] dp = new int[nums.length][sum+1];
        for(int[] n:dp)
            Arrays.fill(n,-1);
        return countSets(nums.length-1,target,nums,dp);
    }

    public int countSets(int index, int target,int[] nums, int[][] dp){
        if(index<0){
            if(target==0){
                return 1;
            }
            return 0;
        }
        if(dp[index][target]!=-1)
            return dp[index][target];

        int nt=countSets(index-1,target,nums,dp);
        int take= 0;
        if(nums[index]<=target)
            take = countSets(index-1,target-nums[index],nums,dp);
        return dp[index][target]=nt+take;
    }

    public static void main(String[] args) {
        PerfectSum_4 perfectSum4 = new PerfectSum_4();
        int count = perfectSum4.perfectSum(new int[]{5, 2, 3, 10, 6, 8},10);//3
        System.out.println(count);
        count = perfectSum4.perfectSum(new int[]{2, 5, 1, 4, 3},10);//3
        System.out.println(count);
        count = perfectSum4.perfectSum(new int[]{5, 7, 8},3);//0
        System.out.println(count);
        count = perfectSum4.perfectSum(new int[]{35, 2, 8, 22},0);//1
        System.out.println(count);
        count = perfectSum4.perfectSum(new int[]{28,4,3,27,0,24,26},24);//2
        System.out.println(count);
    }


}
