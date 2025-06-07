package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class CherryPickUp2_7 {
    //https://leetcode.com/problems/cherry-pickup-ii/
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
