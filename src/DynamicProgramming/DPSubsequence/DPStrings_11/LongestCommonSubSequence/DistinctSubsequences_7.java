package DynamicProgramming.DPSubsequence.DPStrings_11.LongestCommonSubSequence;

import java.util.Arrays;

public class DistinctSubsequences_7 {
    //https://leetcode.com/problems/distinct-subsequences/description/
    /*Given two strings s and t, return the number of distinct subsequences of s which equals t.
    The test cases are generated so that the answer fits on a 32-bit signed integer.
    Example 1:
    Input: s = "rabbbit", t = "rabbit"
    Output: 3
    Explanation:
    As shown below, there are 3 ways you can generate "rabbit" from s.
    rabb b it
    rab b bit
    ra b bbit*/

    //here again we want to find the count
    //hence for the success termination case we return 1
    //also add up the counts of all the choices/recursive calls
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length()+1][t.length()+1];
        for(int[]d:dp)
            Arrays.fill(d,-1);
        return lcsCount(s.length()-1,t.length()-1,dp,s,t);

    }

    public int lcsCount(int i, int j, int[][] dp, String s1,String s2){
        if(j<0)
            return 1;//subseq found
        if(i<0)
            return 0;//the s2 string has not ended and yet s1 is ended so no subseq found
        if(dp[i][j]!=-1)
            return dp[i][j];
        if(s1.charAt(i)==s2.charAt(j)) {
            int cnt1 = lcsCount(i - 1, j - 1, dp, s1, s2);
            int cnt2 = lcsCount(i - 1, j, dp, s1, s2);
            dp[i][j] = cnt1 + cnt2;
        }else {
            dp[i][j] = lcsCount(i - 1, j, dp, s1, s2);
        }
        return dp[i][j];
    }

    public static void main(String[] args) {
        DistinctSubsequences_7 distinctSubsequences7=new DistinctSubsequences_7();
        System.out.println(distinctSubsequences7.numDistinct("rabbbit","rabbit"));
    }
}
