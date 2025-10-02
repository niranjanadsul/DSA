package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class CherryPickUp2_7 {
    //https://leetcode.com/problems/cherry-pickup-ii/
    /*You are given a rows x cols matrix grid representing a field of cherries where grid[i][j]
    represents the number of cherries that you can collect from the (i, j) cell.
    You have two robots that can collect cherries for you:
    Robot #1 is located at the top-left corner (0, 0), and
    Robot #2 is located at the top-right corner (0, cols - 1).

    Return the maximum number of cherries collection using both robots by following the rules below:
    From a cell (i, j), robots can move to cell (i + 1, j - 1), (i + 1, j), or (i + 1, j + 1).
    When any robot passes through a cell, It picks up all cherries, and the cell becomes an empty cell.
    When both robots stay in the same cell, only one takes the cherries.
    Both robots cannot move outside of the grid at any moment.
    Both robots should reach the bottom row in grid.*/

    public int cherryPickup(int[][] grid) {
        int [][][] dp = new int[grid.length][grid[0].length][grid[0].length];
        for(int[][] j1:dp){
            for(int[] j2:j1){
                Arrays.fill(j2,-1);
            }
        }
        return maxCherry(0,0,grid[0].length-1,grid,dp);
    }

    //at any time, both robots will be in same row. Only the columns will be different
    //hence only 3 variable parameters and hence a 3d dp array
    public int maxCherry(int i, int j1, int j2, int[][] grid, int [][][] dp){
        if(j1<0 || j2<0 || j1>=grid[0].length ||j2>=grid[0].length){
            return Integer.MIN_VALUE;
        }
        if(i==grid.length-1){
            if(j1==j2)
                return grid[i][j1];
            else
                return grid[i][j1]+grid[i][j2];
        }
        if(dp[i][j1][j2]!=-1){
            return dp[i][j1][j2];
        }
        int max = Integer.MIN_VALUE;
        for(int k=-1;k<=1;k++){
            for(int l=-1;l<=1;l++){
                if(j1==j2)
                   max = Math.max(grid[i][j1]+maxCherry(i+1,j1+k,j2+l,grid,dp),max);
                else
                    max = Math.max(grid[i][j1]+grid[i][j2]+maxCherry(i+1,j1+k,j2+l,grid,dp),max);
            }
        }
        return dp[i][j1][j2]=max;
    }

    public static void main(String[] args) {
        CherryPickUp2_7 cherryPickUp27=new CherryPickUp2_7();
        int max =cherryPickUp27.cherryPickup(new int[][]{
                {3,1,1},{2,5,1},{1,5,5},{2,1,1}
        });//24
        System.out.println(max);
    }
}
