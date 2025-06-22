package DynamicProgramming.DPMatrixChainMul_7;

import java.util.Arrays;

public class BurstBalloon_3 {
    public int maxCoins(int[] nums) {
        int[] arr=new int[nums.length+2];
        arr[0]=arr[arr.length-1]=1;
        int i=1;
        for(int n:nums)
            arr[i++]=n;
        int[][] dp=new int[nums.length+2][nums.length+2];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        return burst(1,nums.length,arr,dp);
    }

    public int burst(int s, int e, int[] arr, int[][] dp){
        if(s>e)
            return 0;
        if(dp[s][e]!=-1)
            return dp[s][e];
        int maxCoin = 0;
        for(int lastBurstIndex=s;lastBurstIndex<=e;lastBurstIndex++){
            int burstLeft=burst(s,lastBurstIndex-1,arr,dp);
            int burstRight=burst(lastBurstIndex+1,e,arr,dp);
            //left and right neighbour balloons of this batch are bursted
            //so use the neighbour balloons of left batch and right batch i.e s-1 and e+1
            int burstCurrent = arr[s-1]*arr[lastBurstIndex]*arr[e+1];
            maxCoin=Math.max(maxCoin,burstLeft+burstCurrent+burstRight);
        }
        return dp[s][e]=maxCoin;
    }

    public static void main(String[] args) {
        BurstBalloon_3 burstBalloon3=new BurstBalloon_3();
        System.out.println(burstBalloon3.maxCoins(new int[]{3,1,5,8}));//167
    }
}
