package Recursion.BackTracking_7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SudokuSolver_4 {
    public void solveSudoku(char[][] board) {
        Map<Integer,HashSet<Character>> uniqueRows = new HashMap<>();
        Map<Integer,HashSet<Character>> uniqueColumns = new HashMap<>();
        Map<String,HashSet<Character>> uniqueBox = new HashMap<>();

        for(int i = 0;i<board.length;i++){
            HashSet<Character> rowHash = new HashSet<>();
            for(int j = 0;j<board[0].length;j++){
                char c= board[i][j];
                if(c != '.'){
                    rowHash.add(c);
                    uniqueColumns.computeIfAbsent(j,k->new HashSet<>()).add(c);
                    String boxCoord = ""+(i/3)+""+(j/3);
                    uniqueBox.computeIfAbsent(boxCoord,k->new HashSet<>()).add(c);
                }
            }
            uniqueRows.put(i,rowHash);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SudokuSolver_4 sudokuSolver4 = new SudokuSolver_4();
        char[][] board = new char[][]{
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}};
        sudokuSolver4.solveSudoku(board);
    }

}
