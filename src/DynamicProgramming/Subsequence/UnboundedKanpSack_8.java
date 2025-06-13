package DynamicProgramming.Subsequence;

import java.util.Arrays;

public class UnboundedKanpSack_8 {
    //https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
    static int knapSack(int val[], int wt[], int capacity) {
        // code here
        int[][] dp = new int[val.length][capacity+1];
        for(int[] d: dp)
            Arrays.fill(d,-1);
        return fillSack(val.length-1,capacity,val,wt,dp);
    }

    public static int fillSack(int idx, int capacity,int val[], int wt[], int[][] dp){
        if(idx<0 || capacity==0){
            return 0;
        }
        if(dp[idx][capacity]!=-1)
            return dp[idx][capacity];

        int notTake = fillSack(idx-1,capacity,val,wt,dp);
        int take = 0;
        if(wt[idx]<=capacity){
            //infinite supply of item at idx
            take=val[idx]+fillSack(idx,capacity-wt[idx],val,wt,dp);
        }
        return dp[idx][capacity]=Math.max(notTake,take);
    }
}
