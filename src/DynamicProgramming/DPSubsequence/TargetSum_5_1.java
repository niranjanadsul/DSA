package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class TargetSum_5_1 {
    //https://leetcode.com/problems/target-sum/
    // this is similar to CountPartitionsWithGivenDiff_5 problem
    //lets assume that the input array has elements as a,b,c,d,e
    // we want to add operator('+','-') to them so that the sum becomes target
    // let say we do +a+b+c-d-e = (a+b+c)-(d+e)
    // means target = (a+b+c) - (d+e)
    // which is target = s1 - s2
    // also we know totalSum = s1 + s2
    //totalSum - target = 2 * S2
    //s2 = (totalSum - target)/2
    // thus we need to find subsequence with sum (totalSum - target)/2
    // now s2 cannot be a fraction so (totalSum - target) should be even else return 0
    // also (totalSum < target) is not allowed so return 0

    public int findTargetSumWays(int[] nums, int d) {
        // code here
        int sum = 0;
        for(int n:nums)
            sum+=n;
        if(sum<d)
            return 0;
        if((sum-d)%2!=0){
            return 0;
        }

        int target = (sum-d)/2;
        int[][] dp = new int[nums.length][target+1];
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
        TargetSum_5_1 targetSum51 = new TargetSum_5_1();
        int count;
        count= targetSum51.findTargetSumWays(new int[]{1,1,1,1,1},3);//5
        System.out.println(count);

    }
}
