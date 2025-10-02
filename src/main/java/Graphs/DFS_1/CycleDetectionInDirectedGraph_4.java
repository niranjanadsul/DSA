package Graphs.DFS_1;

import java.util.ArrayList;
import java.util.Arrays;

public class CycleDetectionInDirectedGraph_4 {
    //https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1
    /*Given a Directed Graph with V vertices (Numbered from 0 to V-1) and E edges,
    check whether it contains any cycle or not.
    The graph is represented as a 2D vector edges[][],
    where each entry edges[i] = [u, v] denotes an edge from vertex u to v.*/

    //TC=O(V+E)
    //we perform DFS for each node and the DFS is carried out for E edges
    public boolean isCyclic(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildDirectedGraph(V,edges);
        boolean[] visited=new boolean[V];
        boolean[] processing=new boolean[V];
        Arrays.fill(visited,false);
        Arrays.fill(processing,false);
        //there can be multiple components in the graph
        //So we iterate on each unvisited node
        for(int v=0;v<V;v++){
            if(!visited[v]){
                if(dfs(v,visited,processing,adjList))
                    return true;
            }
        }
        return false;
    }

    public boolean dfs(int v,boolean[] visited, boolean[] processing, ArrayList<ArrayList<Integer>> adjList){
        visited[v]=true;
        processing[v]=true;
        for(int neighbour:adjList.get(v)){
            if(!visited[neighbour]){
                if(dfs(neighbour,visited,processing,adjList))
                    return true;//cycle detected while processing the neighbour
            } else if(processing[neighbour])
                return true;//neighbour is already visited and is still processing. Hence cycle found
        }
        processing[v]=false;//processing finished for this node
        return false;//cycle not detected for this node
    }


    public ArrayList<ArrayList<Integer>> buildDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(v);
        }
        return adjList;
    }

    public static void main(String[] args) {
        CycleDetectionInDirectedGraph_4 detectionInDirectedGraph4=new CycleDetectionInDirectedGraph_4();
    }
}
