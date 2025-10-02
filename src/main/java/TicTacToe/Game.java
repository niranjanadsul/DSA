package TicTacToe;

import TicTacToe.Inteface.MoveValidateStrategy;
import TicTacToe.Inteface.WinStrategy;
import TicTacToe.bean.Board;
import TicTacToe.bean.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    private Board board;
    private int currPlayer;
    private int movesRemaining;
    private boolean isWin;
    private final List<Player> players;
    private List<Player> observers;
    private final MoveValidateStrategy moveValidateStrategy;
    private final WinStrategy winStrategy;


    public Game(int size, List<Player> players,
                MoveValidateStrategy moveValidateStrategy, WinStrategy winStrategy) {
        this.players = players;
        this.board = new Board(size);
        this.movesRemaining = size * size;
        this.observers = new ArrayList<>();
        this.moveValidateStrategy = moveValidateStrategy;
        this.winStrategy = winStrategy;
        this.currPlayer = 0;
        this.isWin = false;
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    private void  makeMove(Scanner scanner, Player currentPlayer) {
        while (true) {
            System.out.println("Enter row: 0 to " + this.board.getSize());
            int x = scanner.nextInt();
            System.out.println("Enter column: 0 to " + this.board.getSize());
            int y = scanner.nextInt();


            if (this.moveValidateStrategy.validateMove(x, y, this.board)) {

                currentPlayer.makeMove(x, y, board);
                if (this.winStrategy.checkWin(this.board, currentPlayer, x, y)) {
                    System.out.println("Winner Winner Chicken Dinner " + currentPlayer);
                    this.isWin = true;
                }
                this.movesRemaining--;
                return;
            } else {
                System.out.println("Invalid Move. Please try again");
            }
        }
    }

    public boolean playGame() {
        GameMementoCareTaker gmct = new GameMementoCareTaker();
        Scanner scanner = new Scanner(System.in);

        while (this.movesRemaining > 0) {
            Player currentPlayer = this.players.get(currPlayer);

            System.out.println();
            System.out.println(this.board);
            System.out.println("Turn: " + currentPlayer);

            System.out.println("Options:");
            System.out.println("1. Make move");
            System.out.println("2. Undo last move");
            System.out.println("3. WithDraw Game");
            switch (scanner.nextInt()){
                case 3:
                    return false;
                case 2:
                    if(gmct.hasHistory())
                        this.restoreGame(gmct.undo());
                    break;
                case 1:
                    // store the board
                    gmct.addMemento(this.createMemento());
                    makeMove(scanner, currentPlayer);
                    if(this.isWin)
                        return true;
                    switchToNextPlayer();
            }
        }
        return this.isWin;
    }

    private boolean isDraw() {
        if(!this.isWin) {
            for (int i = 0; i < this.board.getSize(); i++) {
                for (int j = 0; j < this.board.getSize(); j++) {
                    if (this.board.getCell(i, j) == null) {
                        System.out.println("Game is DRAW!!!!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void switchToNextPlayer() {
        this.currPlayer = (this.currPlayer + 1) % this.players.size();
    }

    private GameMemento createMemento(){
        return new GameMemento(this.board.clone(), this.currPlayer, this.movesRemaining);
    }

    private void restoreGame(GameMemento gameMemento){
        this.board = gameMemento.getBoard();
        this.currPlayer = gameMemento.getCurrPlayer();
        this.movesRemaining = gameMemento.getMovesRemaining();
    }
}

