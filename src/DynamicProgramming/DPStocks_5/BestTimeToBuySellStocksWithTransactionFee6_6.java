package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocksWithTransactionFee6_6 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/description/
    /*Find the maximum profit you can achieve. You may complete as many transactions as you like,
     but you need to pay the transaction fee for each transaction.*/
    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length][2]; //buy/sell flag
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return buySell(0,0,fee,dp,prices);
    }

    //we cannot sell before we but stock
    //so first we need to but the stock
    //once the stock is bought we will then check the sell day based on max profit
    //this is achieved by using sell flag
    public int buySell(int idx,int sell,int fee,int[][] dp, int[] prices){
        if(idx>=prices.length)
            return 0;
        if(dp[idx][sell]!=-1)
            return dp[idx][sell];
        int max = Integer.MIN_VALUE;
        if(sell==0) {
            //buy
            //we have 2 options either buy today or don't buy today
            int buyToday = -prices[idx] + buySell(idx+1,1,fee,dp,prices);//buy means deducting the amount
            int buyLater =  buySell(idx+1,sell,fee,dp,prices);
            max = Math.max(buyToday,buyLater);
        }else{
            //sell
            //we have 2 options either sell today or sell later based on maximum stock prices
            //as we can sell multiple times, even after selling today we can try buying later
            //hence we give a buySell call with sell flag as 0
            //if we sell today  then we have to pay transaction fee
            max=Math.max(prices[idx]-fee+buySell(idx+1,0,fee,dp,prices),
                    buySell(idx+1,sell,fee,dp,prices));
        }
        return dp[idx][sell]=max;
    }
}
