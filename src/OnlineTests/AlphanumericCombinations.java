package OnlineTests;

import java.util.Arrays;

public class AlphanumericCombinations {
    public static void main(String[] s){
        String in="123";
        in="2112";
        //in="2101";
        int[] arr = new int[1];
        arr[0]=0;
        alpha(0,1,in,arr);
        System.out.println(arr[0]);

        int[][] dp = new int[in.length()+1][in.length()+1];
        for(int []d:dp)
            Arrays.fill(d,-1);
        System.out.println(alpha2(0,1,in,dp));
    }

    public static void alpha(int s, int e, String in, int[] arr){
        if(s==in.length()){
            arr[0]=arr[0]+1;
            return;
        }
        if(e>in.length()){
            return;
        }
        int num=Integer.parseInt(in.substring(s,e));
        if(num<1 || 26<num)
            return;
        //take partition
        alpha(e,e+1,in, arr);

        //not take partition
        alpha(s,e+1,in,arr);
    }

    public static int alpha2(int s, int e, String in, int[][] dp){
        if(s==in.length()){
            return 1;
        }
        if(e>in.length()){
            return 0;
        }
        if(dp[s][e]!=-1)
            return dp[s][e];
        int num=Integer.parseInt(in.substring(s,e));
        if(num<1 || 26<num)
            return dp[s][e]=0;
        else {
            //take partition
            int tk = alpha2(e, e + 1, in, dp);

            //not take partition
            int nt = alpha2(s, e + 1, in, dp);
            return dp[s][e]=tk+nt;
        }
    }
}
