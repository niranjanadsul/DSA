package Graphs.ShortestPath_4;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SwimInRisingWater_14 {
    //https://leetcode.com/problems/swim-in-rising-water/description/
    /*You are given an n x n integer matrix grid where each value grid[i][j] represents
    the elevation at that point (i, j).
    It starts raining, and water gradually rises over time.
    At time t, the water level is t, meaning any cell with elevation less than equal to t is
    submerged or reachable.
    You can swim from a square to another 4-directionally adjacent square i
    f and only if the elevation of both squares individually are at most t.
    You can swim infinite distances in zero time.
    Of course, you must stay within the boundaries of the grid during your swim.
    Return the minimum time until you can reach the bottom right square (n - 1, n - 1)
    if you start at the top left square (0, 0).*/
    public int swimInWater(int[][] grid) {
        int n=grid.length;
        int[][] distance = new int[n][n];
        boolean[][] visited=new boolean[n][n];

        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                visited[i][j]=false;
            }
        }

        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] a)-> a[0]));
        pq.add(new int[]{grid[0][0],0,0});
        while (!pq.isEmpty()){
            int[] val=pq.remove();
            int dist=val[0];
            int r=val[1];
            int c=val[2];
            visited[r][c]=true;
            distance[r][c]=dist;
            if(r==n-1 && c==n-1)
                return dist;
            int[][] direction=new int[][]{
                    {1,0},{-1,0},{0,1},{0,-1}
            };
            for(int[] dir:direction) {
                int row2 = r + dir[0];
                int col2 = c + dir[1];
                if (0 <= row2 && row2 < n && 0 <= col2 && col2 < n
                        && !visited[row2][col2]) {
                    pq.add(new int[]{Math.max(dist,grid[row2][col2]),row2,col2});
                }
            }
        }
        return distance[n-1][n-1];
    }
}
