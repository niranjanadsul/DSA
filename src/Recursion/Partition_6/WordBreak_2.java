package Recursion.Partition_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak_2 {
    //https://leetcode.com/problems/word-break/description/
    public boolean partition(String s, List<String> dict) {
        int[][] dp = new int[s.length()+1][s.length()+2];
        for(int[] arr:dp){
            Arrays.fill(arr,-1);
        }
        int valid=findPalindrome(0,1,s,dp,dict);
        return valid==1?true:false;
    }

    //partitions start from 1 up to s.length()
    public int findPalindrome(int start, int partition,String s,int[][] dp, List<String> dict){
        if(partition>s.length()){
            if(start>=s.length())
                return dp[start][partition] = 1;
            return dp[start][partition] = 0;
        }
        //dp check
        if(dp[start][partition]!=-1)
            return dp[start][partition];
        //take partition if palindrome
        if(dict.contains(s.substring(start,partition))){
            int ans = findPalindrome(partition,partition+1,s,dp,dict);
            if(ans==1)
                return dp[start][partition] = 1;
        }
        return dp[start][partition] = findPalindrome(start,partition+1,s,dp,dict);
    }

    public static void main(String[] args) {
        WordBreak_2 wordBreak2 = new WordBreak_2();
        System.out.println(wordBreak2.partition("leetcode",Arrays.asList("leet","code")));
        System.out.println(wordBreak2.partition("applepenapple",Arrays.asList("apple","pen")));
        System.out.println(wordBreak2.partition("catsandog",Arrays.asList("cats","dog","sand","and","cat")));
    }

}
