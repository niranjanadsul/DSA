package DynamicProgramming.DPSubsequence.DPStrings_11;

public class LongestCommonSubstring_2 {
    //https://www.geeksforgeeks.org/problems/longest-common-substring1452/1
    /*You are given two strings s1 and s2.
    Your task is to find the length of the longest common substring among the given strings.
    Examples:
    Input: s1 = "ABCDGH", s2 = "ACDGHR"
    Output: 4
    Explanation: The longest common substring is "CDGH" with a length of 4.*/
    //Here we are dealing with continuous substring
    //check the notes
    public int longestCommonSubstr(String s1, String s2) {
        // code here
        int[][] dp = new int[s1.length()+1][s2.length()+1];
        //initialize 1st row
        for(int i =0;i<dp[0].length;i++){
            dp[0][i]=0;
        }
        //initialize 1st column
        for(int i =0;i<dp.length;i++){
            dp[i][0]=0;
        }
        int len = 0;
        for(int i = 1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    int val = dp[i-1][j-1]+1;
                    dp[i][j]=val;
                    len = Math.max(len,val);
                }else {
                    dp[i][j] = 0;
                }
            }
        }
        return len;
    }

    public static void main(String[] args) {
        LongestCommonSubstring_2 longestCommonSubstring2 = new LongestCommonSubstring_2();
        System.out.println(longestCommonSubstring2.longestCommonSubstr("adac","adadac"));
        System.out.println(longestCommonSubstring2.longestCommonSubstr("abcdgh","acdghr"));
        System.out.println(longestCommonSubstring2.longestCommonSubstr("abc","acb"));
    }
}
