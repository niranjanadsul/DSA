package DynamicProgramming.DpSquares_8;

import java.util.Arrays;

public class MaximalSquare_1 {
    //https://leetcode.com/problems/maximal-square/description/
    /*Given an m x n binary matrix filled with 0's and 1's,
    find the largest square containing only 1's and return its area
    Input: matrix = [["1","0","1","0","0"],
    ["1","0","1","1","1"],
    ["1","1","1","1","1"],
    ["1","0","0","1","0"]]
    Output: 4
    */
    public int maximalSquare(char[][] matrix) {
        int max = Integer.MIN_VALUE;
        int[][] dp = new int[matrix.length][matrix[0].length];
        for(int[] d:dp)
            Arrays.fill(d,-1);
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                check(i,j,matrix,dp);
        for(int i=0;i<matrix.length;i++)
            for(int j=0;j<matrix[0].length;j++)
                max=Math.max(max,dp[i][j]);
        return max*max;
    }

    public int check(int i,int j,char[][] matrix, int[][] dp){
        if(i==matrix.length-1 || j==matrix[0].length-1){
            if(matrix[i][j]=='1')
                return dp[i][j]=1;
            return dp[i][j]=0;
        }

        if(dp[i][j]!=-1)
            return dp[i][j];
        if(matrix[i][j]=='0')
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
        MaximalSquare_1 maximalSquare1=new MaximalSquare_1();
        System.out.println(maximalSquare1.maximalSquare(new char[][]{
                {'0','1'},
                {'1','0'}
        }));
        System.out.println(maximalSquare1.maximalSquare(new char[][]{
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        }));
    }
}


