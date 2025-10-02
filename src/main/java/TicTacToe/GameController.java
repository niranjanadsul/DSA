package TicTacToe;

import TicTacToe.Inteface.Implementations.DefaultMoveValidator;
import TicTacToe.Inteface.Implementations.DefaultWinStrategy;
import TicTacToe.bean.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Options:");
        System.out.println("1. Enter board size");
        int size = scanner.nextInt();

        System.out.println("2. Enter number of players");
        int playerCount = scanner.nextInt();
        List<Player> players = new ArrayList<>();
        for(int i = 1;i<=playerCount;i++){
            System.out.println("Enter player "+i+" name");
            String name = scanner.next();
            System.out.println("Enter player "+i+" symbol");
            String symbol = scanner.next();
            players.add(new Player(name,symbol));
        }
        Game game=new Game(size,players,new DefaultMoveValidator(),new DefaultWinStrategy());
        game.playGame();
    }
}
