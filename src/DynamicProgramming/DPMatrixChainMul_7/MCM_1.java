package DynamicProgramming.DPMatrixChainMul_7;

import java.util.Arrays;

public class MCM_1 {
    //https://www.geeksforgeeks.org/problems/matrix-chain-multiplication0303/1
    //TC = O(n*n*n) ==> n*n states/dp array dimension and
    //                  to fill each cell O(n) we need to find the min cost for each partition
    //TC = O(n^3)
    static int matrixMultiplication(int arr[]) {
        //MCM is basically treated as partitioning where the partitioning starts from index 1 till index n-2
        //e.g    A B C
        //      2 1 3 4    ==> matrix dimensions
        //      0 1 2 3    ==>Indices
        //        | |      ==> possible partitions stating at  index 1 up to index n-2
        int[][] dp = new int[arr.length+1][arr.length+1];
        for(int[] d:dp){
            Arrays.fill(d,-1);
        }
        return mcm(1,arr.length-1,dp,arr);
    }

    static int mcm(int start, int end,int[][] dp, int[] arr){
        if(start>= end)
            return 0;
        if(dp[start][end]!=-1)
            return dp[start][end];
        int min = Integer.MAX_VALUE;
        //possible partitions in given start and end is from start to end-1
        for(int part=start;part<end;part++){
            //if we consider current partition then we need to solve left, right and
            // the final left * right
            int count = mcm(start,part,dp,arr) + mcm(part+1,end,dp,arr)+
                    arr[start-1]*arr[part]*arr[end];
            //as we have started from 1 and not from 0
            // so the dimension of left solution will be start-1 X part
            min=Math.min(min,count);
        }
        return dp[start][end]=min;
    }

    public static void main(String[] args) {
        System.out.println(MCM_1.matrixMultiplication(new int[]{2, 1, 3, 4}));//20
    }

}
