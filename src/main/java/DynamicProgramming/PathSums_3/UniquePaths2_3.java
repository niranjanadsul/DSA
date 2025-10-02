package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class UniquePaths2_3 {
    //https://leetcode.com/problems/unique-paths-ii/
    //grid has obstacles
    // time complexity =  O(m*n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length, n=obstacleGrid[0].length;
        int[][] dpArr = new int[m][n];
        for(int i = 0; i<m;i++)
            Arrays.fill(dpArr[i],-1);
        return findPaths(0, 0,m,n,obstacleGrid,dpArr);
    }

    public int findPaths(int i, int j, int m, int n, int [][] arr,int [][] dpArr){
        if(i>m-1 || j>n-1 || arr[i][j]==1){
            return 0;
        }
        if(i==m-1 && j==n-1){
            return 1;
        }

        if(dpArr[i][j] != -1){
            return dpArr[i][j];
        }

        int r = findPaths(i, j+1,m,n,arr,dpArr);
        int b = findPaths(i+1, j,m,n,arr,dpArr);

        return dpArr[i][j] = r + b;
    }

    public static void main(String[] args) {
        UniquePaths2_3 uniquePaths2=new UniquePaths2_3();
        int paths=uniquePaths2.uniquePathsWithObstacles(new int[][]{
                {0,0,0},
                {0,1,0},
                {0,0,0}
        });//2
        System.out.println(paths);
    }
}
