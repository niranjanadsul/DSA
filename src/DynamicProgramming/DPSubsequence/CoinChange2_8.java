package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class CoinChange2_8 {
    //https://leetcode.com/problems/coin-change-ii/description/

    //same as coin change but need to find count of all the possibilities
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[]d: dp){
            Arrays.fill(d,-1);
        }
        return coinsCal(coins.length-1,amount, dp, coins);
    }

    //whenever count of all possible solutions is asked we need to add up take and notTake
    //and always return 1 when the target is achieved
    public int coinsCal(int idx, int target, int[][] dp,int[] coins){
        if(idx==0){
            if(target%coins[idx]==0)
                return 1;
            return 0;
        }
        if(dp[idx][target]!=-1)
            return dp[idx][target];

        int nt = coinsCal(idx-1, target, dp,coins);
        int take = 0;
        if(coins[idx]<=target)
            take = coinsCal(idx,target-coins[idx],dp,coins);

        return dp[idx][target] = take+nt;
    }
}
