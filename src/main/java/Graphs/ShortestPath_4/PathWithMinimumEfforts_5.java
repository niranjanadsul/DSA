package Graphs.ShortestPath_4;

import java.util.*;

public class PathWithMinimumEfforts_5 {
    //https://leetcode.com/problems/path-with-minimum-effort/description/
    /*You are a hiker preparing for an upcoming hike.
    You are given heights, a 2D array of size rows x columns,
    where heights[row][col] represents the height of cell (row, col).
    You are situated in the top-left cell, (0, 0), and
    you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed).
    You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.

    A route's effort is the maximum absolute difference in heights
    between two consecutive cells of the route.

    Return the minimum effort required to travel from the top-left cell to the bottom-right cell.*/

    //Path with min effort === Path with min distance == shortest path ===> Dijkstra's

    //for each cell in 4 directions * time to insert each cell in PQ
    //TC = O(4*N*M* log (N*M))
    public int minimumEffortPath(int[][] grid) {
        int [][] directions= new int[][]{{-1,0},{0,-1}, {0,1},{1,0}};
        int[][] effort = new int[grid.length][grid[0].length];
        for(int[] d:effort)
            Arrays.fill(d,Integer.MAX_VALUE);
        effort[0][0]=0;

        //pq will always give us the min difference first
        PriorityQueue<int[]> priorityQueue=new PriorityQueue<>(Comparator.comparingInt((int[] a)->a[0]));
                        //effort, row, col
        priorityQueue.add(new int[]{0,0,0});
        while (!priorityQueue.isEmpty()){
            int[] val=priorityQueue.remove();
            int r=val[1];
            int c=val[2];
            int parentEffort = val[0];

            //since we are using PQ and we reached the target cell
            //this means this is the min effort and even if there are more entries in the pq
            //they will not be less than current effort as it is PQ
            if(r==grid.length-1 && c==grid[0].length-1)
                return parentEffort;
            for(int[] dir:directions){
                int r1=r+dir[0];
                int c1=c+dir[1];
                if(0<=r1 && r1<grid.length && 0<=c1 && c1<grid[0].length){
                    int neighbourEffort=Math.abs(grid[r][c]-grid[r1][c1]);

                    //A route's effort is the maximum absolute difference in heights
                    //    between two consecutive cells of the route.
                    int newEffort=Math.max(parentEffort,neighbourEffort);

                    if(newEffort<effort[r1][c1]) {
                        effort[r1][c1] = newEffort;
                        priorityQueue.add(new int[]{newEffort,r1, c1});
                    }
                }

            }

        }
        return 0;
    }

    public static void main(String[] args) {
        PathWithMinimumEfforts_5 pathWithMinimumEfforts5=new PathWithMinimumEfforts_5();
        pathWithMinimumEfforts5.minimumEffortPath(new int[][]{{1,10,6,7,9,10,4,9}});
        pathWithMinimumEfforts5.minimumEffortPath(new int[][]{
                {1, 2, 2},
                {3, 8, 2},
                {5, 3, 5}
        });
    }
}
