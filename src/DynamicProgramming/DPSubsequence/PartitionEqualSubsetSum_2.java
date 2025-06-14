package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class PartitionEqualSubsetSum_2 {
    //https://leetcode.com/problems/partition-equal-subset-sum/
    //Time complexity:  O(n*(sum/2))
    public boolean canPartition(int[] arr) {
        //variant of subset sum
        //if sum of array is even then only equal sum partitions will exist
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        if (sum % 2 != 0) {
            return false;
        }
        //if sum is even then we need to use subset sum for sum = sum/2
        sum = sum/2;
        int[][] dp = new int[arr.length][sum+1];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return checkSum(0,sum,arr,dp);
    }

    boolean checkSum(int index, int target, int[] arr,int[][] dp){
        if(target==0)
            return true;
        if(index>=arr.length)
            return false;
        if(dp[index][target]!=-1){
            return dp[index][target]==1;
        }
        //not take
        boolean notTake = checkSum(index+1,target,arr,dp);
        //take
        boolean take =false;
        if(arr[index]<=target){
            take = checkSum(index+1,target-arr[index],arr,dp);
        }
        dp[index][target] = notTake || take?1:0;
        return notTake || take;
    }
}
