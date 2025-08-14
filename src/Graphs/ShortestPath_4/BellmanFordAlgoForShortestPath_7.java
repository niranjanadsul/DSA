package Graphs.ShortestPath_4;

import java.util.Arrays;

public class BellmanFordAlgoForShortestPath_7 {
    //https://www.geeksforgeeks.org/problems/distance-from-the-source-bellman-ford-algorithm/1
    /*Given an weighted graph with V vertices numbered from 0 to V-1 and E edges,
    represented by a 2d array edges[][], where edges[i] = [u, v, w] represents a direct edge from
    node u to v having w edge weight. You are also given a source vertex src.

    Your task is to compute the shortest distances from the source to all other vertices.
    If a vertex is unreachable from the source, its distance should be marked as 108.
    Additionally, if the graph contains a negative weight cycle,
    return [-1] to indicate that shortest paths cannot be reliably computed.*/
    public static int max = 100000000;
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] distance=new int[V];
        Arrays.fill(distance,max);
        distance[src]=0;
        for(int i=1;i<=V;i++){
            boolean isRelaxed=relaxEdge(edges,distance);
            if(i==V && isRelaxed)
                return new int[]{-1};
        }
        return distance;
    }

    public boolean relaxEdge(int[][] edges,int[] distance){
        boolean isRelaxed =false;
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int wt = edge[2];

            if(distance[u]!=max && distance[u]+wt<distance[v]){
                isRelaxed=true;
                distance[v]=distance[u]+wt;
            }
        }
        return isRelaxed;
    }
}
