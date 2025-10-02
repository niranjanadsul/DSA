package DynamicProgramming.DPSubsequence.DPStrings_11.LongestCommonSubSequence;

public class MinInsertionToMakePalindrome_4 {
    //https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/description/
    /*
    Given a string s. In one step you can insert any character at any index of the string.
    Return the minimum number of steps to make s palindrome.
    A Palindrome String is one that reads the same backward as well as forward.
    Example 1:
    Input: s = "zzazz"
    Output: 0
    Explanation: The string "zzazz" is already palindrome we do not need any insertions.*/

    //Same as LCS we will first find the LPS and then ans = totalLength-LPS
    //bcz LPS gives us the length of longest Palindrome possible
    //all the remaining character can be inserted again to make the entire String as palindrome
    public int minInsertions(String s) {
        int lps = longestPalindromeSubseq(s);
        return s.length()-lps;
    }
    public int longestPalindromeSubseq(String s) {
        String s2 = "";
        for(char c:s.toCharArray())
            s2 = c+s2;
        return longestCommonSubsequence(s,s2);
    }

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
