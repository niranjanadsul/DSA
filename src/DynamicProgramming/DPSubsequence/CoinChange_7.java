package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class CoinChange_7 {
    //https://leetcode.com/problems/coin-change/description/
    /*You are given an integer array coins representing coins of different denominations
    and an integer amount representing a total amount of money.
    Return the FEWEST number of coins that you need to make up that amount.
    If that amount of money cannot be made up by any combination of the coins, return -1.
    You may assume that you have an infinite number of each kind of coin.*/
    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[]d: dp){
            Arrays.fill(d,-1);
        }
        int ans = coinsCal(coins.length-1,amount, dp, coins);
        if(ans==Integer.MAX_VALUE-1)
            return -1;
        return ans;
    }

    public int coinsCal(int idx, int target, int[][] dp,int[] coins){
        if(idx==0){
            if(target%coins[idx]==0)
                return target/coins[idx];
            return Integer.MAX_VALUE-1;
        }
        if(dp[idx][target]!=-1)
            return dp[idx][target];

        int ans = coinsCal(idx-1, target, dp,coins);
        if(coins[idx]<=target)
            ans = Math.min(1 + coinsCal(idx,target-coins[idx],dp,coins),ans);
        return dp[idx][target] = ans;
    }

    public static void main(String[] args) {
        CoinChange_7 coinChange6=new CoinChange_7();
        int min;
        min = coinChange6.coinChange(new int[]{1,2,5},11);//3
        System.out.println(min);
    }
}
