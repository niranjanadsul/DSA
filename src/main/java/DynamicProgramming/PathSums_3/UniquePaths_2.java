package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class UniquePaths_2 {
    //https://leetcode.com/problems/unique-paths/description/
    /*There is a robot on an m x n grid. The robot is initially located at the top-left corner
    (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]).
    The robot can only move either down or right at any point in time.
    Given the two integers m and n, return the number of possible unique paths that the robot can
    take to reach the bottom-right corner.
    The test cases are generated so that the answer will be less than or equal to 2 * 109.*/
    public int uniquePaths(int m, int n) {
        int[][] dpArr = new int[m][n];
        for(int i = 0; i<m;i++)
            Arrays.fill(dpArr[i],-1);
        return findPaths(0, 0,m,n,dpArr);
    }

    public int findPaths(int i, int j, int m, int n, int [][] dpArr){
        if(i>m-1 || j>n-1){
            return 0;
        }
        if(i==m-1 && j==n-1){
            return 1;
        }

        if(dpArr[i][j] != -1){
            return dpArr[i][j];
        }

        int r = findPaths(i, j+1,m,n,dpArr);
        int b = findPaths(i+1, j,m,n,dpArr);

        return dpArr[i][j] = r + b;
    }

    public static void main(String[] args) {
        UniquePaths_2 uniquePaths2=new UniquePaths_2();
        int paths=uniquePaths2.uniquePaths(3,7);//28
        System.out.println(paths);
    }
}
