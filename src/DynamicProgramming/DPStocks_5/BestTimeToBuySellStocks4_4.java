package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocks4_4 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/description/
    /*You are given an integer array prices where prices[i]
    is the price of a given stock on the ith day, and an integer k.
    Find the maximum profit you can achieve. You may complete at most k
    transactions: i.e. you may buy at most k times and sell at most k times.*/
    public int maxProfit(int k, int[] prices) {
        int[][] dp = new int[prices.length][k*2]; //buy/sell flag
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return buySell(0,0,dp,prices);
    }

    //we cannot sell before we but stock
    //so first we need to but the stock
    //once the stock is bought we will then check the sell day based on max profit
    //this is achieved by using sell flag
    // as only k transactions are permissible we will have 4 values for sell flag
    //sell == 0, 2 (even values) will indicate buy and sell = 1, 3 (odd) will indicate sell
    public int buySell(int idx,int sell,int[][] dp, int[] prices){
        if(idx>=prices.length || sell>=dp[0].length)
            return 0;
        if(dp[idx][sell]!=-1)
            return dp[idx][sell];
        int max = Integer.MIN_VALUE;
        if(sell%2==0) {
            //buy
            //we have 2 options either buy today or don't buy today
            int buyToday = -prices[idx] + buySell(idx+1,sell+1,dp,prices);//buy means deducting the amount
            int buyLater =  buySell(idx+1,sell,dp,prices);
            max = Math.max(buyToday,buyLater);
        }else {
            //sell
            //we have 2 options either sell today or sell later based on maximum stock prices
            //as we can sell multiple times, even after selling today we can try buying later
            //up to k transactions limit
            //hence we give a buySell call with sell flag as 0
            max=Math.max(prices[idx]+buySell(idx+1,sell+1,dp,prices),
                    buySell(idx+1,sell,dp,prices));
        }
        return dp[idx][sell]=max;
    }
}
