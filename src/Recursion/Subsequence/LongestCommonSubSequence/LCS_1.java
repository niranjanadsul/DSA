package Recursion.Subsequence.LongestCommonSubSequence;

public class LCS_1 {
    //https://leetcode.com/problems/longest-common-subsequence/description/
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();

        int [][] t = new int[n+1][m+1];
        //initialise t
        for(int i=0;i<n+1; i++){
            for(int j=0;j<m+1;j++){
                if(j==0 || i==0){
                    t[i][j]=0;
                }
            }
        }

        //logic for choices
        //always remember or try recursive logic and then move to top down
        for(int i=1;i<n+1; i++){
            for(int j=1;j<m+1;j++){
                if(text1.charAt(i-1)==text2.charAt(j-1)){
                    t[i][j] = 1+t[i-1][j-1];
                }
                else{
                    t[i][j] = Math.max(t[i-1][j],t[i][j-1]);
                }
            }
        }

        return t[n][m];
    }

}
