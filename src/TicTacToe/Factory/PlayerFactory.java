package TicTacToe.Factory;

import TicTacToe.bean.Player;

public class PlayerFactory {
    public static Player createPlayer(String name, String symbol){
        return new Player(name, symbol);
    }
}
