package Graphs.BFS_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class DistanceOfNearestZero_4 {
    //https://leetcode.com/problems/01-matrix/description/
    /*
    * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
      The distance between two cells sharing a common edge is 1.
      Input: mat = [[0,0,0],[0,1,0],[0,0,0]]
      Output: [[0,0,0],[0,1,0],[0,0,0]]*/
    //again here we use multi source BFS by adding all the zero nodes to start BFS with
    //refer the notes
    //TC = O(n*m) + 4*O(n*m) == O(n*m)
    //      initial iteration on input array + iterating on 4 neighbours for each node
    public int[][] updateMatrix(int[][] mat) {
        int[][] distance= new int[mat.length][mat[0].length];
        for(int[] d:distance)
            Arrays.fill(d,Integer.MAX_VALUE);
        Queue<int[]> queue=new LinkedList<>();
        for(int i=0;i<mat.length;i++){
            for(int j=0;j<mat[0].length;j++){
                if(mat[i][j]==0) {
                    distance[i][j] = 0;
                    queue.add(new int[]{i,j});
                }
            }
        }
        int[][] directions = new int[][]{{-1,0},{1,0},{0,-1},{0,1}};
        while (!queue.isEmpty()){
            int[] node = queue.remove();
            int i=node[0];
            int j=node[1];
            int dist=distance[i][j];
            for(int r=0;r<directions.length;r++){
                int newi=i+directions[r][0];
                int newj=j+directions[r][1];
                if(newi>=0 && newi<mat.length && newj>=0 && newj<mat[0].length
                        && mat[newi][newj]!=0 && dist+1<distance[newi][newj]){
                    distance[newi][newj]=dist+1;
                    queue.add(new int[]{newi,newj});
                }
            }
        }
        return distance;
    }

    public static void main(String[] args) {
        DistanceOfNearestZero_4 distance=new DistanceOfNearestZero_4();
        distance.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}});
    }
}
