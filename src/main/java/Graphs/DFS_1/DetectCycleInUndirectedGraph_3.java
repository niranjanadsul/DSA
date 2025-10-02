package Graphs.DFS_1;

import java.util.*;

public class DetectCycleInUndirectedGraph_3 {
    //https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1
    /*Given an undirected graph with V vertices and E edges,
    represented as a 2D vector edges[][], where each entry edges[i] = [u, v] denotes
    an edge between vertices u and v, determine whether the graph contains a cycle or not.

    Examples:

    Input: V = 4, E = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]
    Output: true*/
    //make use of DFS traversal
    //TC = O(V+E)
    // O(E) for initailly building the Adj List
    // O(V) for iterating on each node during multi source BFS
    public boolean isCycle(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildGraph(V,edges);
        boolean[] visited=new boolean[V];
        Arrays.fill(visited,false);
        //there can be multiple components in the graph
        //So we iterate on each unvisited node
        for(int v=0;v<V;v++){
            if(!visited[v]){
                if(detectCycle(v,visited,adjList))
                    return true;
            }
        }
        return false;
    }

    private static boolean detectCycle(int v,boolean[] visited,
                                       ArrayList<ArrayList<Integer>> adjList) {
        Stack<int[]> stack=new Stack<>();
        //currNode,Parent
        stack.push(new int[]{v,-1});
        visited[v]=true;
        while(!stack.isEmpty()){
            int[] arr= stack.pop();
            int currNode = arr[0];
            int parent = arr[1];
            for(int neighbour: adjList.get(currNode)){
                if(neighbour!=parent){
                    if(visited[neighbour])
                        return true;
                    stack.push(new int[]{neighbour,currNode});
                    visited[neighbour]=true;
                }
            }
        }
        return false;
    }

    public ArrayList<ArrayList<Integer>> buildGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(v);
            adjList.get(v).add(u);//undirectional graph
        }
        return adjList;
    }
}
