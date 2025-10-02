package TicTacToe;

import TicTacToe.bean.Board;

public class GameMemento {
    private Board board;
    private int currPlayer;
    private int movesRemaining;

    public GameMemento(Board board, int currPlayer, int movesRemaining){
        this.board = board;
        this.currPlayer = currPlayer;
        this.movesRemaining = movesRemaining;
    }

    public int getMovesRemaining() {
        return movesRemaining;
    }

    public Board getBoard() {
        return board;
    }

    public int getCurrPlayer() {
        return currPlayer;
    }
}
