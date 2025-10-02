package Graphs.ShortestPath_4;

public class FloydWarshallAlgo_12 {
    //https://www.geeksforgeeks.org/problems/implementing-floyd-warshall2042/1
    /*You are given an weighted directed graph, represented by an adjacency matrix,
    dist[][] of size n x n, where dist[i][j] represents the weight of the edge from node i to node j.
    If there is no direct edge, dist[i][j] is set to a large value (i.e., 108) to represent infinity.
        The graph may contain negative edge weights, but it does not contain any negative weight cycles.
        Your task is to find the shortest distance between every pair of nodes i and j in the graph.
        Note: Modify the distances for every pair in place.*/

    public void floydWarshall(int[][] dist) {
        int max = 100000000;
        for(int node=0;node<dist.length;node++){
            for(int i=0;i<dist.length;i++){
                for(int j=0;j<dist.length;j++){
                    if(dist[i][node]!=max && max!=dist[node][j])
                        dist[i][j]=Math.min(dist[i][j],dist[i][node]+dist[node][j]);
                }
            }
        }
    }
}
