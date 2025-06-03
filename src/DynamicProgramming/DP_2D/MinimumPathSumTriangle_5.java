package DynamicProgramming.DP_2D;

import java.util.Arrays;
import java.util.List;

public class MinimumPathSumTriangle_5 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[][] dp = new int[triangle.size()][triangle.get(triangle.size()-1).size()];
        for(int i = 0;i<dp.length;i++){
            Arrays.fill(dp[i],-1);
        }
        return findPath(0,0,triangle,dp);
    }

    public int findPath(int i,int j, List<List<Integer>> grid, int[][] dp){
        if(i==grid.size()-1){
            return grid.get(i).get(j);
        }
        if(i>= grid.size()){
            return Integer.MAX_VALUE;
        }
        if(dp[i][j]!=-1)
            return dp[i][j];

        int downRight = findPath(i+1,j+1,grid,dp);
        int down = findPath(i+1,j,grid,dp);
        return dp[i][j] = grid.get(i).get(j)+Math.min(downRight,down);
    }
}
