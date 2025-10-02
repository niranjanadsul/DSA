package TicTacToe.Inteface.Implementations;

import TicTacToe.Inteface.WinStrategy;
import TicTacToe.bean.Board;
import TicTacToe.bean.Player;

public class DefaultWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, Player player,int x, int y) {
        return checkRow(board,player,x) || checkColumn(board,player,y)
                || checkDiagonal(board,player) || checkAntiDiagonal(board,player);
    }

    private boolean checkRow(Board board, Player player,int row){
        for(int i=0;i<board.getSize();i++){
            if(board.getCell(row,i)!=player){
                return false;
            }
        }
        return true;

    }

    private boolean checkColumn(Board board, Player player,int column){
        for(int i=0;i<board.getSize();i++){
            if(board.getCell(i,column)!=player){
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(Board board, Player player) {
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, i) != player) {
                return false;
            }
        }
        return true;
    }

    private boolean checkAntiDiagonal(Board board, Player player) {
        int size = board.getSize();
        for (int i = 0; i < size; i++) {
            if (board.getCell(i, size - 1 - i) != player) {
                return false;
            }
        }
        return true;
    }
}
