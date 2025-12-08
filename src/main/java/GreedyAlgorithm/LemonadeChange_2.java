package GreedyAlgorithm;

import java.util.Arrays;

public class LemonadeChange_2 {
    //https://leetcode.com/problems/lemonade-change/description/
    /*At a lemonade stand, each lemonade costs $5.
    Customers are standing in a queue to buy from you and order one at a time
    (in the order specified by bills). Each customer will only buy one lemonade and
    pay with either a $5, $10, or $20 bill.
    You must provide the correct change to each customer so that the net transaction
    is that the customer pays $5.

    Note that you do not have any change in hand at first.

    Given an integer array bills where bills[i] is the bill the ith customer pays,
    return true if you can provide every customer with the correct change,
    or false otherwise.



    Example 1:

    Input: bills = [5,5,5,10,20]
    Output: true
    Explanation:
    From the first 3 customers, we collect three $5 bills in order.
    From the fourth customer, we collect a $10 bill and give back a $5.
    From the fifth customer, we give a $10 bill and a $5 bill.
    Since all customers got correct change, we output true.
    Example 2:

    Input: bills = [5,5,10,10,20]
    Output: false
    Explanation:
    From the first two customers in order, we collect two $5 bills.
    For the next two customers in order, we collect a $10 bill and give back a $5 bill.
    For the last customer, we can not give the change of $15 back because we only have
    two $10 bills.
    Since not every customer received the correct change, the answer is false.*/
    public boolean lemonadeChange(int[] bills) {
        int[] coins=new int[3];
        Arrays.fill(coins,0);//initial no change
        for(int bill:bills){
            switch (bill){
                case 5:coins[0]++;
                break;
                case 10:coins[1]++;
                    if(!canGiveChange(coins,bill-5))
                        return false;
                break;
                default:coins[2]++;
                    if(!canGiveChange(coins,bill-5))
                        return false;
            }
        }
        return true;
    }

    public boolean canGiveChange(int[] coins,int bill){
        bill=checkChange(coins,2,20,bill);
        if (bill==0)
            return true;
        bill=checkChange(coins,1,10,bill);
        if(bill==0)
            return true;
        bill=checkChange(coins,0,5,bill);
        if(bill==0)
            return true;
        return false;
    }

    public int checkChange(int[] coins,int index,int coin, int bill){
        int count=coins[index];
        if(count>0 && coin<=bill && bill/coin<=count){
            coins[index]-=bill/coin;
            bill=bill%coin;
        }
        return bill;
    }

    public static void main(String[] args) {
        LemonadeChange_2 lemonadeChange2=new LemonadeChange_2();
        System.out.println(lemonadeChange2.lemonadeChange(new int[]{5,5,5,10,20}));//true
        System.out.println(lemonadeChange2.lemonadeChange(new int[]{5,5,10,10,20}));//false
    }
}
