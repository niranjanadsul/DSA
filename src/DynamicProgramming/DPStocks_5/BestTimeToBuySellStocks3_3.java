package DynamicProgramming.DPStocks_5;

import java.util.Arrays;

public class BestTimeToBuySellStocks3_3 {
    //https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/description/
    /*Find the maximum profit you can achieve. You may complete at most two transactions.
    Note: You may not engage in multiple transactions simultaneously
    (i.e., you must sell the stock before you buy again).*/
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length][4]; //buy/sell flag
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return buySell(0,0,dp,prices);
    }

    //we cannot sell before we but stock
    //so first we need to but the stock
    //once the stock is bought we will then check the sell day based on max profit
    //this is achieved by using sell flag
    // as only 2 transactions are permissible we will have 4 values for sell flag
    //sell == 0, 2 will indicate buy and sell = 1, 3 will indicate sell
    public int buySell(int idx,int sell,int[][] dp, int[] prices){
        if(idx>=prices.length || sell>3)
            return 0;
        if(dp[idx][sell]!=-1)
            return dp[idx][sell];
        int max = Integer.MIN_VALUE;
        if(sell==0 || sell==2) {
            //buy
            //we have 2 options either buy today or don't buy today
            int buyToday = -prices[idx] + buySell(idx+1,sell+1,dp,prices);//buy means deducting the amount
            int buyLater =  buySell(idx+1,sell,dp,prices);
            max = Math.max(buyToday,buyLater);
        }else if(sell == 1 || sell == 3){
            //sell
            //we have 2 options either sell today or sell later based on maximum stock prices
            //as we can sell multiple times, even after selling today we can try buying later
            //up to 2 transactions limit
            //hence we give a buySell call with sell flag as 0
            max=Math.max(prices[idx]+buySell(idx+1,sell+1,dp,prices),
                    buySell(idx+1,sell,dp,prices));
        }
        return dp[idx][sell]=max;
    }

    public static void main(String[] args) {
        BestTimeToBuySellStocks3_3 bestTimeToBuySellStocks1=new BestTimeToBuySellStocks3_3();
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{3,3,5,0,0,3,1,4}));//6
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{1,2,3,4,5}));//4
        System.out.println(bestTimeToBuySellStocks1.maxProfit(new int[]{7,6,4,3,1}));//0
    }
}
