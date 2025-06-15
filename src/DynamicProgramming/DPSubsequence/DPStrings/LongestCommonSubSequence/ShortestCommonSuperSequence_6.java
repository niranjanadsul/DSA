package DynamicProgramming.DPSubsequence.DPStrings.LongestCommonSubSequence;

public class ShortestCommonSuperSequence_6 {
    //https://leetcode.com/problems/shortest-common-supersequence/description/
    //SCS is a string that can generate both the inout strings
    //E.g s1 = abc s2 = bed . Here LCS = b
    // scs = abced. So here we neet to combine both strings and remove the LCS only once to get SCS
    //to get the SCS we will do same as printing LCS but if at an i and j if the characters are not same
    //then compare left cell and upper cell,
    //if left cell is greater then add s2[j-1] to SCS and move left
    //else add s1[i-1] to SCS and move up
    // at last there may be some characters remaining which we need to iterate until i>0 and j>0
    //T.C. = O(n*m + (n+m-k))
    //     = O(n*m)
    public String shortestCommonSupersequence(String text1, String text2) {
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

        String scs="";
        int i=n,j=m;
        while(i>0 && j>0){
            if(text1.charAt(i-1)==text2.charAt(j-1)){
                scs=text1.charAt(i-1)+scs;
                i--;
                j--;
            }else{
                if(t[i][j-1]>t[i-1][j]){
                    scs=text2.charAt(j-1)+scs;
                    j--;
                }else{
                    scs=text1.charAt(i-1)+scs;
                    i--;
                }
            }
        }

        while(i>0){
            scs=text1.charAt(i-1)+scs;
            i--;
        }

        while (j>0){
            scs=text2.charAt(j-1)+scs;
            j--;
        }

        return scs;
    }
}
