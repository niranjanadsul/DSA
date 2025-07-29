package Graphs.DFS_1;

import java.util.ArrayList;
import java.util.Arrays;

public class ConnectedComponentsInUnDirectedGraph_2 {
    //https://www.geeksforgeeks.org/problems/connected-components-in-an-undirected-graph/1
    /*Given an undirected graph with V vertices numbered from 0 to V-1 and E edges,
    represented as a 2D array edges[][], where each entry edges[i] = [u, v] denotes
    an edge between vertices u and v.
    Your task is to return a list of all connected components.
    Each connected component should be represented as a list of its vertices,
    with all components returned in a collection where each component is listed separately.
    Note: You can return the components in any order,
    driver code will print the components in sorted order.*/

    //iterate over the edges array and create an adjacency list
    //iterate over each vertex
    //if unvisited then call dfs traversal for it

    //TC = O(E+V)Visiting each vertex once:
    //The DFS algorithm ensures that each vertex is visited at most once.
    // This contributes O(V) to the time complexity.

    //Traversing each edge once:
    //When a vertex is visited, DFS iterates through its adjacency list to explore its neighbors.
    // Since the graph is represented using an adjacency list,
    // each edge (u, v) is traversed twice in an undirected graph (once from u to v and once from v to u)
    // or once in a directed graph. In total, this process involves examining all edges, contributing O(E)
    // to the time complexity.

    //Overall Complexity:
    //Combining these two aspects, the total time complexity becomes O(V + E).
    // This is considered efficient as it is linear with respect to the size of the graph
    // (number of vertices and edges).
    /**/
    public ArrayList<ArrayList<Integer>> getComponents(int V, int[][] edges) {
        ArrayList<ArrayList<Integer>> adjList = buildGraph(V,edges);
        ArrayList<ArrayList<Integer>> connectedComponent = new ArrayList<>();
        int[] visited = new int[V];
        Arrays.fill(visited,0);
        for(int i=0;i<V;i++){
            if(visited[i]!=1){
                ArrayList<Integer> component = new ArrayList<>();
                dfs(i,adjList,visited,component);
                connectedComponent.add(component);
            }
        }
        return connectedComponent;
    }

    public ArrayList<ArrayList<Integer>> buildGraph(int nodes,int[][] edges){
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

    public void dfs(int vertex, ArrayList<ArrayList<Integer>> adjList, int[] visited,
                    ArrayList<Integer> component){
        visited[vertex]=1;
        component.add(vertex);
        for(int neighbour:adjList.get(vertex)){
            if(visited[neighbour]!=1)
                dfs(neighbour,adjList,visited,component);
        }
    }
}
