package DynamicProgramming.Subsequence;

import java.util.Arrays;

public class CountPartitionsWithGivenDiff_5 {
    //https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1
    //here s1 - s2 =diff
    // s1+s2 = totSum
    // 2S1 = totSum + diff
    // s1 = (totSum + diff)/2
    // therefore we need to count the partitions with s1 = (totSum + diff)/2
    // but if s1 is odd then return 0

    int countPartitions(int[] nums, int d) {
        // code here
        int sum = 0;
        for(int n:nums)
            sum+=n;
        if((sum+d)%2!=0){
            return 0;
        }

        int target = (sum+d)/2;
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
        CountPartitionsWithGivenDiff_5 countPartitionsWithGivenDiff5 = new CountPartitionsWithGivenDiff_5();
        int count = countPartitionsWithGivenDiff5.countPartitions(new int[]{1, 2, 1, 0, 1, 3, 3},11);//2
        System.out.println(count);
        count = countPartitionsWithGivenDiff5.countPartitions(new int[]{1,3,3,2,1},5);//0
        System.out.println(count);
        count = countPartitionsWithGivenDiff5.countPartitions(new int[]{2,3,5,1,3,4,5,4,4,5,2,4,1,1},48);//0
        System.out.println(count);
    }
}
