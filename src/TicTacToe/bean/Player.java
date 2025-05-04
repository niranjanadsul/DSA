package TicTacToe.bean;

public class Player {
    String name;
    String symbol;

    public Player(String name, String symbol){
        this.name= name;
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void makeMove(int x, int y, Board board){
        board.updateBoard(x, y, this);
    }

    @Override
    public String toString() {
        return this.name+" ("+this.symbol+")";
    }
}
