package TicTacToe.Inteface;

import TicTacToe.bean.Board;
import TicTacToe.bean.Player;

public interface WinStrategy {
    public boolean checkWin(Board board, Player player, int x, int y);
}
