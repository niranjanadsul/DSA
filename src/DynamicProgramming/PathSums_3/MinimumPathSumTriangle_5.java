package DynamicProgramming.PathSums_3;

import java.util.Arrays;
import java.util.List;

public class MinimumPathSumTriangle_5 {
    //https://leetcode.com/problems/unique-paths-ii/description/
    /*Given a triangle array, return the minimum path sum from top to bottom.
    For each step, you may move to an adjacent number of the row below.
    More formally, if you are on index i on the current row,
    you may move to either index i or index i + 1 on the next row.
    Example 1:
    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    Output: 11
    Explanation: The triangle looks like:
            2
            3 4
            6 5 7
            4 1 8 3
    The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11 (underlined above).*/

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
