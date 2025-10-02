package TicTacToe.Inteface;

import TicTacToe.bean.Board;

public interface MoveValidateStrategy {
    public boolean validateMove(int x, int y, Board board);
}
