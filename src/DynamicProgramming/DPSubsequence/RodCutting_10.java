package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class RodCutting_10 {
    //https://www.geeksforgeeks.org/problems/rod-cutting0840/1
    /*Given a rod of length n inches and an array price[],
    where price[i] denotes the value of a piece of length i.
    Your task is to determine the maximum value obtainable by cutting up the rod and selling the pieces.
    Note: n = size of price, and price[] is 1-indexed array.*/

    //this problem can be treated same as unbounded knapsack
    //consider the price array as values
    //length of the rod as the capacity of knapsack
    //each index of price array + 1 as the size of the rod
    public int cutRod(int[] price) {
        // code here
        int[][] dp = new int[price.length][price.length+1];
        for(int[] d: dp)
            Arrays.fill(d,-1);
        return fillSack(price.length-1,price.length,price,dp);
    }

    public static int fillSack(int idx, int capacity,int val[], int[][] dp){
        if(idx<0 || capacity==0){
            return 0;
        }
        if(dp[idx][capacity]!=-1)
            return dp[idx][capacity];

        int notTake = fillSack(idx-1,capacity,val,dp);
        int take = 0;
        int len = idx+1;
        if(len<=capacity){
            //infinite supply of item at idx
            take=val[idx]+fillSack(idx,capacity-len,val,dp);
        }
        return dp[idx][capacity]=Math.max(notTake,take);
    }
}
