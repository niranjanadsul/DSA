package OnlineTests;

import java.util.Arrays;

public class AlphanumericCombinations {
    public static void main(String[] s){
        String in="123";
        in="314241414346644541652433556252665423311365641144551513243152623366656365516344432643125444163114545266353646436155611516162544434342253552516212225664435126342612366162364456225214466124311533435356363";
        //in="2101";
        int[] arr = new int[1];
        arr[0]=0;
        //alpha(0,1,in,arr);
        //System.out.println(arr[0]);

        long[][] dp = new long[in.length()+1][in.length()+1];
        for(long []d:dp)
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

    public static long alpha2(int s, int e, String in, long[][] dp){
        if(s==in.length()){
            return 1L;
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
            long tk = alpha2(e, e + 1, in, dp);

            //not take partition
            long nt = alpha2(s, e + 1, in, dp);
            return dp[s][e]=tk+nt;
        }
    }
}
