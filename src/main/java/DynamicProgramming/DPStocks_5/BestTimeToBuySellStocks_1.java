package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocks_1 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock/description/
    /*You are given an array prices where prices[i] is the price of a given stock on the ith day.

    You want to maximize your profit by choosing a single day to buy one stock and
    choosing a different day in the future to sell that stock.

    Return the maximum profit you can achieve from this transaction.
    If you cannot achieve any profit, return 0.

    Example 1:

    Input: prices = [7,1,5,3,6,4]
    Output: 5
    Explanation: Buy on day 2 (price = 1) and sell on day 5 (price = 6), profit = 6-1 = 5.
    Note that buying on day 2 and selling on day 1 is not allowed because you must buy before you sell.*/
    //T.C = O(n*2)
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
            max=Math.max(prices[idx],buySell(idx+1,sell,dp,prices));
        }
        return dp[idx][sell]=max;
    }

    public static void main(String[] args) {
        BestTimeToBuySellStocks_1 bestTimeToBuySellStocks1=new BestTimeToBuySellStocks_1();
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{7,1,5,3,6,4}));//5
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{7,6,4,3,1}));//0
    }
}
