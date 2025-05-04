package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class GameMementoCareTaker {
    private List<GameMemento> history;

    public GameMementoCareTaker(){
        this.history = new ArrayList<>();
    }

    public boolean hasHistory(){
        return !this.history.isEmpty();
    }

    public void addMemento(GameMemento gm){
        this.history.add(gm);
    }

    public GameMemento undo(){
        GameMemento gm = this.history.getLast();
        this.history.removeLast();
        return gm;
    }
}
