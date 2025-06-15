package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocks2_2 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/description/
    /*You are given an integer array prices where prices[i] is the price
    of a given stock on the ith day.
    On each day, you may decide to buy and/or sell the stock.
    You can only hold at most one share of the stock at any time. However,
    you can buy it then immediately sell it on the same day.
    Find and return the maximum profit you can achieve*/
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][2]; //buy/sell flag
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return buySell(0,0,dp,prices);
    }

    //we cannot sell before we but stock
    //so first we need to but the stock
    //once the stock is bought we will then check the sell day based on max profit
    //this is achieved by using sell flag
    public int buySell(int idx,int sell,int[][] dp, int[] prices){
        if(idx>=prices.length)
            return 0;
        if(dp[idx][sell]!=-1)
            return dp[idx][sell];
        int max = Integer.MIN_VALUE;
        if(sell==0) {
            //buy
            //we have 2 options either buy today or don't buy today
            int buyToday = -prices[idx] + buySell(idx+1,1,dp,prices);//buy means deducting the amount
            int buyLater =  buySell(idx+1,sell,dp,prices);
            max = Math.max(buyToday,buyLater);
        }else{
            //sell
            //we have 2 options either sell today or sell later based on maximum stock prices
            //as we can sell multiple times, even after selling today we can try buying later
            //hence we give a buySell call with sell flag as 0
            max=Math.max(prices[idx]+buySell(idx+1,0,dp,prices),
                    buySell(idx+1,sell,dp,prices));
        }
        return dp[idx][sell]=max;
    }

    public static void main(String[] args) {
        BestTimeToBuySellStocks2_2 bestTimeToBuySellStocks1=new BestTimeToBuySellStocks2_2();
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{7,1,5,3,6,4}));//7
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{1,2,3,4,5}));//4
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{7,6,4,3,1}));//0
    }
}
