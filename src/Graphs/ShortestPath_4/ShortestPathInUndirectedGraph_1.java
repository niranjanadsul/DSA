package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInUndirectedGraph_1 {
    //https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1
    /*
    * You are given an adjacency list, adj of Undirected Graph having unit weight of the edges,
    * find the shortest path from src to all the vertex and if it is unreachable to reach any vertex,
    * then return -1 for that vertex.*/

    //this problem is simply a BFS traversal from the start node
    //we will maintain distance array that'll help to store and compare distance of each node from src node
    //also we will compare the distance of adjacent nodes while adding them to queue.

    //As this is an undirected graph BFS will need T.C = O(V+2E)
    //iterate each node and explore the neighbour twice as edge is undirected

    public int[] shortestPath(ArrayList<ArrayList<Integer>> adj, int src) {
        Queue<Integer> queue=new LinkedList<>();
        int[] distance = new int[adj.size()];

        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[src]=0; //distance of source node is 0

        queue.add(src);

        while (!queue.isEmpty()){
            int node = queue.remove();
            int currDist = distance[node];
            for(int adjNode:adj.get(node)){
                if(currDist+1<distance[adjNode]){
                    //adjNode is closer from currentNode
                    distance[adjNode]=currDist+1;
                    queue.add(adjNode);
                }
            }
        }

        for(int i=0;i<distance.length;i++)
            if(distance[i]==Integer.MAX_VALUE)
                distance[i]=-1;
        return distance;
    }
}
