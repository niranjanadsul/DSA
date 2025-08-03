package Graphs.BFS_2;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class IsGraphBipartite_10 {
    //https://leetcode.com/problems/is-graph-bipartite/description/
    /*There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1.
    You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to.
    More formally, for each v in graph[u], there is an undirected edge between node u and node v.
    The graph has the following properties:

    There are no self-edges (graph[u] does not contain u).
    There are no parallel edges (graph[u] does not contain duplicate values).
    If v is in graph[u], then u is in graph[v] (the graph is undirected).
    The graph may not be connected, meaning there may be two nodes u and v such that there is no path
    between them.
    A graph is bipartite if the nodes can be partitioned into two independent sets A and B such
    that every edge in the graph connects a node in set A and a node in set B.

    Return true if and only if it is bipartite.

    Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
    Output: false
    Explanation: There is no way to partition the nodes into two independent sets such that
    every edge connects a node in one and a node in the other.

    Input: graph = [[1,3],[0,2],[1,3],[0,2]]
    Output: true
    Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.*/

    //use the two colour approach
    //Time Complexity: O(V + 2E),
    // Where V = Vertices, 2E is for total degrees as we traverse all adjacent nodes.
    public boolean isBipartite(int[][] graph) {
        int[] colour=new int[graph.length];
        Arrays.fill(colour,-1);
        for(int i=0;i<graph.length;i++){
            if(colour[i]==-1) {
                colour[i] = 0;
                if(!bfs(i,graph,colour))
                    return false;
            }
        }
        return true;
    }

    public boolean bfs(int node,int[][] graph,int[] colour){
        Queue<Integer> queue=new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty()){
            int i=queue.remove();
            for(int j=0;j<graph[i].length;j++){
                int neighbour=graph[i][j];
                if(colour[neighbour]==colour[i])
                    return false;
                if(colour[neighbour]==-1){
                    queue.add(neighbour);
                    colour[neighbour]=(colour[i]+1)%2;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsGraphBipartite_10 isGraphBipartite10=new IsGraphBipartite_10();
        System.out.println(isGraphBipartite10.isBipartite(new int[][]{
                {1,3},{0,2},{1,3},{0,2}
        }));//true
        System.out.println(isGraphBipartite10.isBipartite(new int[][]{
                {1,2,3},{0,2},{0,1,3},{0,2}
        }));//false
    }
}
