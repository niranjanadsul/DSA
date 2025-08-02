package Graphs.BFS_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SurroundedRegion_5 {
    //https://leetcode.com/problems/surrounded-regions/
    /*You are given an m x n matrix board containing letters 'X' and 'O',
    capture regions that are surrounded:
    Connect: A cell is connected to adjacent cells horizontally or vertically.
    Region: To form a region connect every 'O' cell.
    Surround: The region is surrounded with 'X' cells if you can connect the region with 'X' cells and
                none of the region cells are on the edge of the board.
    To capture a surrounded region,
    replace all 'O's with 'X's in-place within the original board. You do not need to return anything.*/
    //we will find all the 0 on the boundary and then find out their regions
    //a region means number of cells
    //now all 0 that do not belong to the identified regions need to be captured
    //T.C = O(N*M)
    public void solve(char[][] board) {
        Queue<int[]> queue=new LinkedList<>();
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        for(int i =0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(i==0 || i== board.length-1 || j==0 || j ==board[0].length-1){
                    //this is border cell
                    if(board[i][j]=='O'){
                        queue.add(new int[]{i,j});
                    }
                }
            }
        }
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int i = node[0];
            int j = node[1];
            board[i][j]='Y';
            for (int r = 0; r < directions.length; r++) {
                int newi = i + directions[r][0];
                int newj = j + directions[r][1];
                if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length
                        && board[newi][newj]=='O'){
                    queue.add(new int[]{newi,newj});
                }
            }
        }

        for(int i =0;i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                if(board[i][j]=='O')
                    board[i][j]='X';
                if(board[i][j]=='Y')
                    board[i][j]='O';
            }
        }
    }

    public static void main(String[] args) {
        SurroundedRegion_5 surroundedRegion5=new SurroundedRegion_5();
        surroundedRegion5.solve(new char[][]{
                {'O','O','O'},
                {'O','O','O'},
                {'O','O','O'}});
        surroundedRegion5.solve(new char[][]{
                {'X','X','X','X'},
                {'X','O','O','X'},
                {'X','X','O','X'},
                {'X','O','X','X'}});
    }
}
