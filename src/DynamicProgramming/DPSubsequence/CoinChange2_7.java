package DynamicProgramming.DPSubsequence;

import java.util.Arrays;

public class CoinChange2_7 {
    //same a coin change but need to find count of all the possibilities
    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length][amount+1];
        for(int[]d: dp){
            Arrays.fill(d,-1);
        }
        return coinsCal(coins.length-1,amount, dp, coins);
    }

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
