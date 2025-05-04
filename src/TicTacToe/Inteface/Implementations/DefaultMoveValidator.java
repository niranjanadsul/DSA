package TicTacToe.Inteface.Implementations;

import TicTacToe.Inteface.MoveValidateStrategy;
import TicTacToe.bean.Board;

public class DefaultMoveValidator implements MoveValidateStrategy {
    @Override
    public boolean validateMove(int x, int y, Board board) {
        return board.isCellAvailable(x, y);
    }
}
