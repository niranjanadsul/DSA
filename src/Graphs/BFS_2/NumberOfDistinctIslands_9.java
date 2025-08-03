package Graphs.BFS_2;

import java.util.*;

public class NumberOfDistinctIslands_9 {
    //https://www.geeksforgeeks.org/problems/number-of-distinct-islands/1
    /*
    * Given a boolean 2D matrix grid of size n * m.
    * You have to find the number of distinct islands where a group of connected 1s
    * (horizontally or vertically) forms an island.
    * Two islands are considered to be distinct if and only if one island is not equal to another
    * (not rotated or reflected).
    *
    * Input:
        grid[][] = [[1, 1, 0, 0, 0],
                    [1, 1, 0, 0, 0],
                    [0, 0, 0, 1, 1],
                    [0, 0, 0, 1, 1]]
        Output: 1
        Explanation:
        grid[][] = [[1, 1, 0, 0, 0],
                    [1, 1, 0, 0, 0],
                    [0, 0, 0, 1, 1],
                    [0, 0, 0, 1, 1]]
        Same colored islands are equal.
        We have 2 equal islands, so we
        have only 1 distinct island.
        *
        * Example 2:

        Input:
        grid[][] = [[1, 1, 0, 1, 1],
                    [1, 0, 0, 0, 0],
                    [0, 0, 0, 0, 1],
                    [1, 1, 0, 1, 1]]
        Output: 3
        Explanation:
        grid[][] = [[1, 1, 0, 1, 1],
                    [1, 0, 0, 0, 0],
                    [0, 0, 0, 0, 1],
                    [1, 1, 0, 1, 1]]
        Same colored islands are equal.
        We have 4 islands, but 2 of them
        are equal, So we have 3 distinct islands.*/

    //TC = O(N*M)
    //make sure you need to use a custom class for storing coordinates and overide the equals method
    //otherwise hashset will not compare the List and treat them different

    int countDistinctIslands(int[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        boolean[][] visited = new boolean[n][m];
        for(boolean[] v:visited){
            Arrays.fill(v,false);
        }
        // carry out multi source BFS by using visited array
        HashSet<ArrayList<Coordinate>> set=new HashSet<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                //call bfs for unvisited cell with value 1
                //well this will be the Base Cell for a particular Island
                if(grid[i][j]==1 && !visited[i][j]) {
                    ArrayList<Coordinate> ls = bfs(i, j, grid, visited);
                    set.add(ls);
                }
            }
        }
        return set.size();
    }

    public ArrayList<Coordinate> bfs(int baseI,int baseJ, int[][] board, boolean[][] visited){
        Queue<int[]> queue=new LinkedList<>();
        ArrayList<Coordinate> ls =new ArrayList<>();
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        queue.add(new int[]{baseI,baseJ});
        visited[baseI][baseJ]=true;
        while (!queue.isEmpty()) {
            int[] node = queue.remove();
            int i = node[0];
            int j = node[1];
            ls.add(new Coordinate(i - baseI, j - baseJ));
            for (int r = 0; r < directions.length; r++) {
                int newi = i + directions[r][0];
                int newj = j + directions[r][1];
                if(newi>=0 && newi<board.length && newj>=0 && newj<board[0].length
                        && board[newi][newj]==1 && !visited[newi][newj]){
                    visited[newi][newj]=true;
                    queue.add(new int[]{newi,newj});
                }
            }
        }
        return ls;
    }

    class Coordinate{
        int x,y;
        Coordinate(int x, int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coordinate that = (Coordinate) o;
            return x == that.x && y == that.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static void main(String[] args) {
        NumberOfDistinctIslands_9 numberOfDistinctIslands9=new NumberOfDistinctIslands_9();
        System.out.println(numberOfDistinctIslands9.countDistinctIslands(new int[][]{
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 1, 1, 0},
                {0, 0, 1, 0, 1},
                {1, 1, 0, 1, 1}}));//3
    }

}
