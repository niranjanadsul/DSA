package DynamicProgramming.DpSquares_8;

import java.util.Arrays;

public class CountSquares_2 {
    //https://leetcode.com/problems/count-square-submatrices-with-all-ones/
    /*Given a m * n matrix of ones and zeros, return how many square submatrices have all ones.
      Example 1:
      Input: matrix =
                [
                [0,1,1,1],
                [1,1,1,1],
                [0,1,1,1]
                ]
        Output: 15
        Explanation:
        There are 10 squares of side 1.
        There are 4 squares of side 2.
        There is  1 square of side 3.
        Total number of squares = 10 + 4 + 1 = 15
    */
    //we just need to sum up the dp array to get the count of squares with all 1's
    public int countSquares(int[][] matrix) {
        int sum=0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                check(i,j,matrix,dp);
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                sum+=dp[i][j];
        return sum;
    }

    public int check(int i,int j,int[][] matrix, int[][] dp){
        if(i==matrix.length-1 || j==matrix[0].length-1){
            if(matrix[i][j]==1)
                return dp[i][j]=1;
            return dp[i][j]=0;
        }

        if(dp[i][j]!=-1)
            return dp[i][j];
        if(matrix[i][j]==0)
            return dp[i][j]=0;

        int min=Integer.MAX_VALUE;
        int[][] itr = new int[][]{{0,1},{1,1},{1,0}};
        for(int[] inc:itr){
            int c = check(i+inc[0],j+inc[1],matrix,dp);
            min=Math.min(min,c);
        }
        return dp[i][j]=min+1;
    }

    public static void main(String[] args) {
        CountSquares_2 countSquares=new CountSquares_2();
        System.out.println(countSquares.countSquares(new int[][]{
                {0,1,1,1},
                {1,1,1,1},
                {0,1,1,1}
        }));//15
        System.out.println(countSquares.countSquares(new int[][]{
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1},
                {1,1,1,1}
        }));//30
    }
}
