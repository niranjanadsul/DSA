package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class MinimumFallingPathSum_6 {
    public int minFallingPathSum(int[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int[] d:dp){
            Arrays.fill(d,Integer.MAX_VALUE);
        }
        int min = Integer.MAX_VALUE;
        for(int j=0;j<matrix[0].length;j++){
            min = Math.min(min,minSum(0,j,matrix,dp));
        }
        return min;
    }

    public int minSum(int i,int j,int[][] matrix, int[][] dp){
        if(j<0 || j>=matrix[0].length)
            return Integer.MAX_VALUE;
        if(i==matrix.length-1)
            return matrix[i][j];
        if(dp[i][j]!=Integer.MAX_VALUE)
            return dp[i][j];
        int leftD = minSum(i+1,j-1,matrix,dp);
        int down = minSum(i+1,j,matrix,dp);
        int rightD = minSum(i+1,j+1,matrix,dp);
        return dp[i][j]=matrix[i][j]+Math.min(leftD,Math.min(down,rightD));
    }

    public static void main(String[] args) {
        MinimumFallingPathSum_6 minimumFallingPathSum=new MinimumFallingPathSum_6();
        int min = minimumFallingPathSum.minFallingPathSum(new int[][]{
                {2,1,3},
                {6,5,4},
                {7,8,9}
        });
        System.out.println(min);//13
    }


}
