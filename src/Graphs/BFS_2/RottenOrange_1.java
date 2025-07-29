package Graphs.BFS_2;

import java.util.LinkedList;
import java.util.Queue;

public class RottenOrange_1 {
    //https://leetcode.com/problems/rotting-oranges/
    /*You are given an m x n grid where each cell can have one of three values:
    0 representing an empty cell,
    1 representing a fresh orange, or
    2 representing a rotten orange.
    Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
    Return the minimum number of minutes that must elapse until no cell has a fresh orange.
    If this is impossible, return -1.
    * */

    //we need to process all the neighbour first hence we think of Multi Source BFS

    //TC = O(n*m)
    //iterating the grid to find the initially find the rotten oranges
    public int orangesRotting(int[][] grid) {
        int toConvert =0;
        int converted =0;
        Queue<int[]> queue= new LinkedList<>();
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    toConvert++;
                }
                if(grid[i][j]==2){
                    queue.add(new int[]{i,j,0});//add all the rotten oranges to the queue initially
                }
            }
        }
        int[][] directions=new int[][]{{1,0},{0,1},{-1,0},{0,-1}};
        int timeToRot=0;
        while (!queue.isEmpty()){
            int size = queue.size();
            for(int i=0;i<size;i++){
                int[] rotCell=queue.remove();
                timeToRot=rotCell[2];
                for(int[] arr:directions){
                    int row=rotCell[0]+arr[0];
                    int col=rotCell[1]+arr[1];
                    if(row>=0 && row<grid.length && col>=0 && col<grid[0].length && grid[row][col]==1){
                        grid[row][col]=2; //ro this fresh orange
                        converted++;
                        queue.add(new int[]{row,col,timeToRot+1});
                    }
                }
            }
        }

        if(toConvert !=converted){
            timeToRot=-1;//some of the oranges did not rot
        }
        return timeToRot;
    }
}
