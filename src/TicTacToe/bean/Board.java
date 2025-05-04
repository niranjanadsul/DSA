package TicTacToe.bean;

import java.util.ArrayList;
import java.util.List;

public class Board implements Cloneable{
    private int size;
    private Player[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Player[size][size];
    }

    public void updateBoard(int x, int y, Player player){
        this.grid[x][y]= player;
    }

    public Player getCell(int x, int y){
        return this.grid[x][y];
    }

    public boolean isCellAvailable(int x, int y){
        return x>=0 && x<grid.length && y>=0 && y<grid[0].length && this.grid[x][y]==null;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        String b = "";

        for(int i=0;i<grid.length;i++){
            Player[] row=grid[i];
            b+="|";
            for(int j=0;j<row.length;j++){
                Player p = row[j];
                String symbol = p!=null?p.getSymbol()+" ":i+""+j;
                b+=symbol+"|";
            }
            b+="\n";
        }
        return b;
    }

    @Override
    public Board clone(){
        Player[][] grid = new Player[this.size][this.size];
        Board clone= new Board(this.size);
        for(int i=0;i<grid.length;i++) {
            for (int j = 0; j < this.grid[0].length; j++) {
                grid[i][j]=this.grid[i][j];
            }
        }
        clone.grid=grid;
        return clone;
    }
}
