package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class MinimumPathSumInGrid_4 {
    //https://leetcode.com/problems/minimum-path-sum/
    /*Given a m x n grid filled with non-negative numbers,
    find a path from top left to bottom right, which minimizes the sum of all numbers along its path.
    Note: You can only move either down or right at any point in time.*/

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for(int i = 0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return findPath(0,0,grid,dp);
    }

    public int findPath(int i,int j, int[][] grid, int[][] dp){
        if(i==grid.length-1 && j==grid[0].length-1){
            return grid[i][j];
        }
        if(i>= grid.length || j>= grid[0].length){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j]!=-1)
            return dp[i][j];

        int right = findPath(i,j+1,grid,dp);
        int down = findPath(i+1,j,grid,dp);
        return dp[i][j] = grid[i][j]+Math.min(right,down);
    }

    public static void main(String[] args) {
        MinimumPathSumInGrid_4 minimumPathSumInGrid4=new MinimumPathSumInGrid_4();
        int min = minimumPathSumInGrid4.minPathSum(new int[][]{
                {1,3,1},{1,5,1},{4,2,1}
        });//7
        System.out.println(min);
    }
}
