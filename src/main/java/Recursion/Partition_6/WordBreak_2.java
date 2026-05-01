package Recursion.Partition_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordBreak_2 {
    //https://leetcode.com/problems/word-break/description/
    /*Given a string s and a dictionary of strings wordDict,
    return true if s can be segmented into a space-separated sequence of one or more dictionary
    words.
    Note that the same word in the dictionary may be reused multiple times in the segmentation.
    Example 1:

    Input: s = "leetcode", wordDict = ["leet","code"]
    Output: true
    Explanation: Return true because "leetcode" can be segmented as "leet code".
    Example 2:

    Input: s = "applepenapple", wordDict = ["apple","pen"]
    Output: true
    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
    Note that you are allowed to reuse a dictionary word.
    Example 3:
    Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
    Output: false*/
    public boolean partition(String s, List<String> dict) {
        int[][] dp = new int[s.length()+1][s.length()+2];
        for(int[] arr:dp){
            Arrays.fill(arr,-1);
        }
        int valid=findPalindrome(0,1,s,dp,dict);
        return valid == 1;
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
