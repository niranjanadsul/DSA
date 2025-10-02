package DynamicProgramming.DPMatrixChainMul_7;

import java.util.Arrays;

public class PalindromePartition2_4 {
    public int minCut(String s) {
        int[] dp = new int[s.length()];
        Arrays.fill(dp,-1);
        return cut(0,s.length(),dp,s.toCharArray())-1;
    }

    public int cut(int s, int e, int[] dp, char[] arr){
        if(s>=e)
            return 0;
        if(dp[s]!=-1)
            return dp[s];
        int min = Integer.MAX_VALUE;
        for(int k=s+1;k<=e;k++){
            //take the partition only if the left substring is pallindrome
            if(isPal(s,k-1,arr)){
                min = Math.min(min,1+cut(k,e,dp,arr));
            }
        }
        return dp[s]=min;
    }

    public boolean isPal(int s, int e, char[] arr){
        while(s<e){
            if(arr[s]!=arr[e])
                return false;
            s++;
            e--;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartition2_4 palindromePartition24=new PalindromePartition2_4();
        System.out.println(palindromePartition24.minCut("bb"));//0
        System.out.println(palindromePartition24.minCut("aab"));//1
        System.out.println(palindromePartition24.minCut("a"));//0
        System.out.println(palindromePartition24.minCut("ab"));//1
    }
}
