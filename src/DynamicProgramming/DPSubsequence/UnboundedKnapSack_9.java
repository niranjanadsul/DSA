package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class UnboundedKnapSack_9 {
    //https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1
    /*Given a set of items, each with a weight and a value, represented by the array wt and
    val respectively. Also, a knapsack with a weight limit capacity.
    The task is to fill the knapsack in such a way that we can get the maximum profit.
    Return the maximum profit.
    Note: Each item can be taken any number of times.*/
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
