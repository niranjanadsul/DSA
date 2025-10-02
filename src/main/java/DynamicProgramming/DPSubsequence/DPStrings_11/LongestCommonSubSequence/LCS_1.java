package DynamicProgramming.DPSubsequence.DPStrings_11.LongestCommonSubSequence;

public class LCS_1 {
    //https://leetcode.com/problems/longest-common-subsequence/description/
    /*Given two strings text1 and text2, return the length of their longest common subsequence.
     If there is no common subsequence, return 0.
    A subsequence of a string is a new string generated from the original string with
     some characters (can be none) deleted without changing the relative order of the
     remaining characters.
    For example, "ace" is a subsequence of "abcde".
    A common subsequence of two strings is a subsequence that is common to both strings.*/
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

    public static void main(String[] args) {
        LCS_1 lcs1=new LCS_1();
        System.out.println(lcs1.longestCommonSubsequence("abaaa","baabaca"));
    }

}
