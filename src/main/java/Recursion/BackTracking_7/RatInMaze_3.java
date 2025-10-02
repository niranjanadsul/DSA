package Recursion.BackTracking_7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class RatInMaze_3 {
    //https://www.geeksforgeeks.org/problems/rat-in-a-maze-problem/1&selectedLang=python3
    public ArrayList<String> ratInMaze(int[][] maze) {
        // code here
        ArrayList<String> allpaths = new ArrayList<>();
        int[][] vis = new int[maze.length][maze[0].length];
        for(int[] a: vis ){
            Arrays.fill(a,-1);
        }
        ratRun(0,0,"",vis, maze,allpaths);
        Collections.sort(allpaths);
        return allpaths;
    }

    public void ratRun(int i, int j, String curr, int[][] vis,int[][] maze, ArrayList<String> paths){
        if(i== maze.length-1 && j == maze[0].length-1){
            paths.add(curr);
            return;
        }
        if(i<0 || i>=maze.length || j<0 || j>=maze[0].length || vis[i][j]!=-1 || maze[i][j]==0)
            return;

        vis[i][j] = 1;
        ratRun(i+1,j,curr+"D",vis,maze,paths);
        ratRun(i-1,j,curr+"U",vis,maze,paths);
        ratRun(i,j+1,curr+"R",vis,maze,paths);
        ratRun(i,j-1,curr+"L",vis,maze,paths);
        vis[i][j] = -1;
    }

    public static void main(String[] args) {
        RatInMaze_3 ratInMaze3 = new RatInMaze_3();
        int[][] arr = new int[][]{
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {1, 1, 0, 0},
                {0, 1, 1, 1}
        };
        System.out.println(ratInMaze3.ratInMaze(arr));
    }
}
