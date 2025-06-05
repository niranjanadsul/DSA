package DynamicProgramming.PathSums_3;

import java.util.Arrays;

public class UniquePaths_2 {
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
