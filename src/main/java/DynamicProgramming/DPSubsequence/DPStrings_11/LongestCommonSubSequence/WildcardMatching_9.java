package DynamicProgramming.DPSubsequence.DPStrings_11.LongestCommonSubSequence;

import java.util.Arrays;

public class WildcardMatching_9 {
    //https://leetcode.com/problems/wildcard-matching/
    /*Given an input string (s) and a pattern (p), implement wildcard pattern
    matching with support for '?' and '*' where:

            '?' Matches any single character.
            '*' Matches any sequence of characters (including the empty sequence).
    The matching should cover the entire input string (not partial).
    Example 1:

    Input: s = "aa", p = "a"
    Output: false
    Explanation: "a" does not match the entire string "aa".
    */
    public boolean isMatch(String s, String p) {
        int[][] dp = new int[s.length()+1][p.length()+1];
        for(int[]d:dp)
            Arrays.fill(d,-1);
        int ans = match(s.length()-1,p.length()-1,s,p,dp);
        return ans==1;
    }

    public int match(int i,int j,String s, String p, int[][] dp){
        if(i<0){
            //this is the edge case
            //if String s is completed but the pattern still has * at starting few characters
            // then we need to iterate on these *
            while(j>=0){
                if(p.charAt(j)=='*')
                    j--;
                else
                    return 0;
            }
            return 1;
        }
        if(j<0){
            return 0;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int ans = 0;
        if(s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')
            ans = match(i-1,j-1,s,p,dp);
        else if(p.charAt(j)=='*'){
            //* indicates 0 or more characters
            int ans1 = match(i,j-1,s,p,dp);  // 0 character
            int ans2 = match(i-1,j,s,p,dp);  // more characters
            ans = ans1==1 || ans2==1 ? 1 : 0;
        }
        return dp[i][j]=ans;
    }

    public static void main(String[] args) {
        WildcardMatching_9 wildcardMatching9 = new WildcardMatching_9();
        System.out.println(wildcardMatching9.isMatch("abcabczzzde","*abc???de*"));
    }
}
