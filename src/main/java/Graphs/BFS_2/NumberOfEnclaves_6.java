package Graphs.BFS_2;

import java.util.LinkedList;
import java.util.Queue;

public class NumberOfEnclaves_6 {
    //https://leetcode.com/problems/number-of-enclaves/description/
    /*
    * You are given an m x n binary matrix grid,
    * where 0 represents a sea cell and 1 represents a land cell.
    A move consists of walking from one land cell to another adjacent (4-directionally) land cell
    * or walking off the boundary of the grid.
    Return the number of land cells in grid for which we cannot walk off the boundary
    * of the grid in any number of moves.*/
    //can be solved in same way as earlier problem
    /*
    * Traverse the border (first row, last row, first column, last column).
       For every land cell (1) on the border, use BFS to mark all land cells connected to it as 0 (water).
        After flood-filling the boundary-connected lands,
        * count all the remaining 1s in the grid — these are the enclaves!*/
    //TC=O(N*M)
    public int numEnclaves(int[][] board) {
        Queue<int[]> queue=new LinkedList<>();
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        int count=0;
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==1)
                    count++;
                if(i==0 || i== board.length-1 || j==0 || j ==board[0].length-1){
                    //this is border cell
                    if(board[i][j]==1){
                        queue.add(new int[]{i,j});
                        board[i][j]=0;
                        //this is very important to change the cell value while adding the cell to queue
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int i = node[0];
            int j = node[1];
            count--;
            for (int r = 0; r < directions.length; r++) {
                int newi = i + directions[r][0];
                int newj = j + directions[r][1];
                if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length
                        && board[newi][newj]==1){
                    queue.add(new int[]{newi,newj});
                    board[newi][newj]=0;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        NumberOfEnclaves_6 numberOfEnclaves6=new NumberOfEnclaves_6();
        numberOfEnclaves6.numEnclaves(new int[][]{
                {0,1,1,0},
                {0,0,1,0},
                {0,0,1,0},
                {0,0,0,0}});
    }
}
