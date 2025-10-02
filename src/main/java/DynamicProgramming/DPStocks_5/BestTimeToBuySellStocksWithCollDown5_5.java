package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocksWithCollDown5_5 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/description/
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
            //if we sell stock today then we need to COOLDOWN for next 1 day
            //hence we will increase index by 2
            max=Math.max(prices[idx]+buySell(idx+2,0,dp,prices),
                    buySell(idx+1,sell,dp,prices));
        }
        return dp[idx][sell]=max;
    }

    public static void main(String[] args) {
        BestTimeToBuySellStocksWithCollDown5_5 bestTimeToBuySellStocksWithCollDown55=new
                BestTimeToBuySellStocksWithCollDown5_5();
        System.out.println(bestTimeToBuySellStocksWithCollDown55.maxProfit(new int[]{1,2,3,0,2}));//3
    }
}
