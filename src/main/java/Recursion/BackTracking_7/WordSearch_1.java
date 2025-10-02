package Recursion.BackTracking_7;

import java.util.Arrays;

public class WordSearch_1 {
    //https://leetcode.com/problems/word-search/description/
    //Time complexity
    // As iterate on each cell off the grid and
    // for each cell we keep checking in 4 directions till depth = length of string (l)
    // O(m * n * 4^l)
    public boolean exist(char[][] board, String word) {
        int[][] vis = new int[board.length][board[0].length];
        for(int[] v: vis) {
            Arrays.fill(v,-1);
        }
        for (int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length;j++) {
                if (findWord(i, j, 0, word.toCharArray(), board, vis))
                    return true;
            }
        }
        return false;
    }

    public boolean findWord(int i, int j, int currChar, char[] chars, char[][] board,
                            int[][] vis){
        if(currChar == chars.length)
            return true;
        if(i<0 || i>=board.length || j<0 || j>=board[0].length || vis[i][j]!=-1)
            return false;
        if(chars[currChar]!=board[i][j])
            return false;
        vis[i][j]=1;
        boolean ans =  findWord(i,j-1,currChar+1,chars,board,vis) ||
                findWord(i,j+1,currChar+1,chars,board,vis) ||
                findWord(i-1,j,currChar+1,chars,board,vis) ||
                findWord(i+1,j,currChar+1,chars,board,vis);
        vis[i][j]=-1;
        return ans;
    }

    public static void main(String[] args) {
        WordSearch_1 wordSearch1 = new WordSearch_1();
        char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(wordSearch1.exist(board,"ABCCED"));
        System.out.println(wordSearch1.exist(board,"SEE"));
        System.out.println(wordSearch1.exist(board,"ABCB"));
    }
}
