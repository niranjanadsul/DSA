package Recursion.BackTracking_7;

import java.util.*;

public class SudokuSolver_4 {
    public void solveSudoku(char[][] board) {
        Map<Integer,HashSet<Character>> uniqueRows = new HashMap<>();
        Map<Integer,HashSet<Character>> uniqueColumns = new HashMap<>();
        Map<String,HashSet<Character>> uniqueBox = new HashMap<>();
        List<int[]> emptyPositions = new ArrayList<>();

        boolean valid = validateBoard(board, uniqueColumns, uniqueBox, uniqueRows,emptyPositions);
        if(valid){
            solve(0,board,uniqueColumns,uniqueBox,uniqueRows,emptyPositions);
        }
    }

    public boolean validateBoard(char[][] board, Map<Integer, HashSet<Character>> uniqueColumns,
                                 Map<String, HashSet<Character>> uniqueBox, Map<Integer,
            HashSet<Character>> uniqueRows, List<int[]> emptyPositions) {
        for(int i = 0; i< board.length; i++){
            HashSet<Character> rowHash = new HashSet<>();
            for(int j = 0; j< board[0].length; j++){
                String boxCoord = ""+(i/3)+""+(j/3);
                char c= board[i][j];
                if(c != '.'){
                    if(rowHash.contains(c))
                        return false;
                    rowHash.add(c);
                    if(uniqueColumns.computeIfAbsent(j, k->new HashSet<>()).contains(c))
                        return false;
                    uniqueColumns.computeIfAbsent(j, k->new HashSet<>()).add(c);
                    if(uniqueBox.computeIfAbsent(boxCoord, k->new HashSet<>()).contains(c))
                        return false;
                    uniqueBox.computeIfAbsent(boxCoord, k->new HashSet<>()).add(c);
                }else{
                    uniqueColumns.computeIfAbsent(j, k->new HashSet<>());
                    uniqueBox.computeIfAbsent(boxCoord, k->new HashSet<>());
                    emptyPositions.add(new int[]{i,j});
                }
            }
            uniqueRows.put(i,rowHash);
        }
        return true;
    }

    public boolean solve(int index,char[][] board,
                            Map<Integer, HashSet<Character>> uniqueColumns,
                            Map<String, HashSet<Character>> uniqueBox,
                            Map<Integer, HashSet<Character>> uniqueRows, List<int[]> emptyPositions){
        if(index==emptyPositions.size()){
            return true;
        }
        int i =emptyPositions.get(index)[0];
        int j =emptyPositions.get(index)[1];
        String boxCoord = ""+(i/3)+""+(j/3);
        for(char c='1';c<='9';c++){
            if(!uniqueRows.get(i).contains(c) && !uniqueColumns.get(j).contains(c) &&
                    !uniqueBox.get(boxCoord).contains(c)){
                board[i][j] = c;
                uniqueRows.get(i).add(c);
                uniqueColumns.get(j).add(c);
                uniqueBox.get(boxCoord).add(c);
                if(solve(index+1,board,uniqueColumns,uniqueBox,uniqueRows,emptyPositions)){
                    return true;
                }
                board[i][j] = '.';
                uniqueRows.get(i).remove(c);
                uniqueColumns.get(j).remove(c);
                uniqueBox.get(boxCoord).remove(c);
            }
        }
        return false;
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
        System.out.println();
    }

}
