package Recursion.Subsequence;

import java.util.Arrays;
import java.util.HashMap;

public class MColouringProblem_6 {
    /*
    * You are given an undirected graph consisting of V vertices and
    *  E edges represented by a list edges[][], along with an integer m.
    * Your task is to determine whether it is possible to color the graph using at most m
    *  different colors such that no two adjacent vertices share the same color.
    *  Return true if the graph can be colored with at most m colors, otherwise return false.

    Note: The graph is indexed with 0-based indexing.

    Examples:

    Input: V = 4, edges[][] = [[0, 1], [1, 3], [2, 3], [3, 0], [0, 2]], m = 3
    Output: true
    Explanation: It is possible to color the given graph using 3 colors, for example, one of the possible ways vertices can be colored as follows:

    Vertex 0: Color 1
    Vertex 1: Color 2
    Vertex 2: Color 2
    Vertex 3: Color 3*/
    boolean graphColoring(int v, int[][] edges, int m) {
        int [][] adj = new int[v][v];
        for(int[] node: adj){
            Arrays.fill(node,-1);
        }
        for(int [] edge:edges){
            int s = edge[0];
            int e = edge[1];
            adj[s][e]=1;
            adj[e][s]=1;
        }
        int [] colours= new int[v];
        Arrays.fill(colours,-1);
        return giveColour(0,colours,adj,m);
    }

    boolean giveColour(int node, int[] colours, int [][] adj, int m){
        if(node>=adj.length)
            return true;

        for(int i=0;i<m;i++){
            if(isColourValid(node,i,colours,adj)){
                int temp = colours[node];
                colours[node]=i;
                boolean ans = giveColour(node+1,colours,adj,m);
                if(ans)
                    return true;
                colours[node]= temp;
            }
        }
        return false;
    }

    public boolean isColourValid(int node, int colur, int[] colours, int [][] adj){
        for(int i=0;i<adj.length;i++){
            if(adj[node][i] != -1){
                if(colours[i]==colur)
                    return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        MColouringProblem_6 mColouringProblem6 = new MColouringProblem_6();
        int[][] edges = new int[][]{{0, 1}, {1, 3}, {2, 3}, {3, 0}, {0, 2}};
        boolean x = mColouringProblem6.graphColoring(4,edges,3);//true
        System.out.println(x);
        edges = new int[][]{{0, 1}, {1, 2}, {0, 2}};
        x = mColouringProblem6.graphColoring(3,edges,2);//false
        System.out.println(x);
    }
}
