package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix_4 {
    //https://leetcode.com/problems/shortest-path-in-binary-matrix/description/
    /*Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix.
    If there is no clear path, return -1.
    A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0))
    to the bottom-right cell (i.e., (n - 1, n - 1)) such that:

    All the visited cells of the path are 0.
    All the adjacent cells of the path are 8-directionally connected
    (i.e., they are different and they share an edge or a corner).
    The length of a clear path is the number of visited cells of this path.*/

    //here the words Shortest path remind us about Dijkstra's algo/BFS But with simple queue
    //we can add the neighbouring nodes with 0 in the Queue with the distance
    // TC=O(N*N*8)
    public int shortestPathBinaryMatrix(int[][] grid) {
        /*{{-1,-1},{-1,0},{-1,1},
            {0,-1},{0,1},
            {1,-1},{1,0},{1,1}}*/
        if(grid[0][0]==1 || grid[grid.length-1][grid.length-1]==1)
            return -1;
        int[][] distance = new int[grid.length][grid.length];
        for(int[] d:distance)
            Arrays.fill(d,Integer.MAX_VALUE);
        distance[0][0]=0;
        Queue<int[]> queue=new LinkedList<>();
        queue.add(new int[]{0,0,0});
        while (!queue.isEmpty()){
            int[] val=queue.remove();
            int r=val[0];
            int c=val[1];
            int cost=val[2];
            for(int i =-1;i<=1;i++){
                for(int j=-1;j<=1;j++){
                    if(i==0 && j==0)continue;
                    int r1=r+i;
                    int c1=c+j;
                    if(0<=r1 && r1<grid.length && 0<=c1 && c1<grid.length &&
                            grid[r1][c1]==0 && cost+1<distance[r1][c1]){
                        distance[r1][c1]=cost+1;
                        queue.add(new int[]{r1,c1,cost+1});
                    }
                }
            }

        }
        if(distance[grid.length-1][grid.length-1]==Integer.MAX_VALUE)
            return -1;
        return distance[grid.length-1][grid.length-1]+1;
    }
}
