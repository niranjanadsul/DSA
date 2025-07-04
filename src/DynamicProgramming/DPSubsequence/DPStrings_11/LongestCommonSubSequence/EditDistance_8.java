package DynamicProgramming.DPSubsequence.DPStrings_11.LongestCommonSubSequence;

import java.util.Arrays;

public class EditDistance_8 {
    //https://leetcode.com/problems/edit-distance/
    /*Given two strings word1 and word2, return the minimum number of operations
    required to convert word1 to word2.

    You have the following three operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character


    Example 1:

    Input: word1 = "horse", word2 = "ros"
    Output: 3
    Explanation:
    horse -> rorse (replace 'h' with 'r')
    rorse -> rose (remove 'r')
    rose -> ros (remove 'e')
    */
    //this is same as lcs use two pointers i and j
    // for each unmatched character we have 3 choices insert/delete/replace
    // the i and j will change as per the operation considered
    // if we insert the character
    // then the hypothetical insertion will happen ahead of the i hence i remains same. j reduces by 1
    //if character is hypothetically replaced then characters are matched. we can reduce both i and j by 1
    //if character is hypothetically deleted
    // then it means character represented by j is still not matched hence only reduce i by 1

    //if the characters represented by i and j are matched then both i and j will reduce by 1
    public int minDistance(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int[]d:dp)
            Arrays.fill(d,-1);
        return calMinDistance(s.length()-1,t.length()-1,dp,s,t);
    }

    public int calMinDistance(int i, int j, int [][] dp, String s, String e){
        if(j<0){
            //this means all character from target string are matched
            // the characters that remain in the source string have to be all deleted
            return i+1;
        }
        if(i<0){
            // some characters from target string are not yet matched but the input string is finished
            // so we have to insert the remaining characters from target string into the source string
            return j+1;
        }
        if(dp[i][j]!=-1){
            return dp[i][j];
        }
        int min = Integer.MAX_VALUE;
        if(s.charAt(i)== e.charAt(j)){
            min=calMinDistance(i-1,j-1,dp,s,e);
        }else{
            //3 options
            //insert a matching character in source string ahead of i
            int ins = 1+calMinDistance(i,j-1,dp,s,e);
            //replace the i th character from source by j th character from target
            int replace = 1 + calMinDistance(i-1,j-1,dp,s,e);
            //delete te the unmatched i th character from source
            int del = 1+ calMinDistance(i-1,j,dp,s,e);
            min = Math.min(ins,Math.min(replace,del));
        }
        dp[i][j] = min;
        return  min;
    }
}
