package DynamicProgramming.DPMatrixChainMul_7;

import java.util.Arrays;

public class PartitionArrayMaxSum_5 {
    //https://leetcode.com/problems/partition-array-for-maximum-sum/description/
    /*Given an integer array arr, partition the array into (contiguous) subarrays of length at most k.
    After partitioning, each subarray has their values changed to become the maximum value of that subarray.
    Return the largest sum of the given array after partitioning.
    Test cases are generated so that the answer fits in a 32-bit integer.
    Example 1:
    Input: arr = [1,15,7,9,2,5,10], k = 3
    Output: 84
    Explanation: arr becomes [15,15,15,9,10,10,10]*/

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int[] dp = new int[arr.length];
        Arrays.fill(dp,-1);
        return partition(0,k,arr,dp);
    }

    public int partition(int s, int k,int[] arr, int[] dp){
        if(s>=arr.length)
            return 0;
        if(dp[s]!=-1)
            return dp[s];

        int maxElement = arr[s];
        int maxSum = 0;
        //we can consider AT MOST k elements from index s considering the element at index s
        for(int len=0;len<k;len++){
            int index =s+len;
            if(index>=arr.length)
                break;
            maxElement=Math.max(maxElement,arr[index]);
            maxSum=Math.max(maxSum,
                    (len+1)*maxElement+partition(index+1,k,arr,dp));
                    //sum of max element so Far and find the max from next partition
        }
        return dp[s]=maxSum;
    }

    public static void main(String[] args) {
        PartitionArrayMaxSum_5 partitionArrayMaxSum5=new PartitionArrayMaxSum_5();
        System.out.println(partitionArrayMaxSum5.maxSumAfterPartitioning(
                new int[]{1,15,7,9,2,5,10},3));//84
        System.out.println(partitionArrayMaxSum5.maxSumAfterPartitioning(
                new int[]{1,4,1,5,7,3,6,1,9,9,3},4));//83
        System.out.println(partitionArrayMaxSum5.maxSumAfterPartitioning(
                new int[]{1},1));//1
    }
}
