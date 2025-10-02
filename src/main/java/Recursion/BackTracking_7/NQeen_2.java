package Recursion.BackTracking_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQeen_2 {
    public List<List<String>> solveNQueens(int n) {
        int[][] board = new int[n][n];
        for(int[] a:board){
            Arrays.fill(a,-1);
        }
        List<List<String>> allCombinations = new ArrayList<>();
        placeQueens(0,board,allCombinations);
        return allCombinations;
    }

    public void placeQueens(int row,int[][] board,List<List<String>> allCombinations){
        if(row==board.length) {
            allCombinations.add(convertBoardToString(board));
            return;
        }
        //place the queen row by row
        for(int i=0;i<board[row].length;i++){
            if(canPlaceQueen(row,i,board)){
                board[row][i] = 1;
                placeQueens(row+1,board,allCombinations);
                board[row][i] = -1;
            }
        }
    }

    public List<String> convertBoardToString(int[][] board){
        List<String> ls = new ArrayList<>();
        for(int i=0; i<board.length;i++){
            StringBuilder s = new StringBuilder();
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]==1)
                    s.append("Q");
                else
                    s.append(".");
            }
            ls.add(s.toString());
        }
        return ls;
    }

    public boolean canPlaceQueen(int i, int j, int[][] board){
        int row=i-1,col=j-1;
        while(row>=0 && col>=0){
            if(board[row][col]!=-1)
                return false;
            row--;
            col--;
        }
        row=i-1;col=j;
        while(row>=0){
            if(board[row][col]!=-1)
                return false;
            row--;
        }
        row=i-1;col=j+1;
        while(row>=0 && col<board[0].length){
            if(board[row][col]!=-1)
                return false;
            row--;
            col++;
        }
        return true;
    }

    public static void main(String[] args) {
        NQeen_2 nQeen2=new NQeen_2();
        System.out.println(nQeen2.solveNQueens(4));
    }
}
