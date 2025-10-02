package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class DijkstrasAlgorithm_3 {
    //https://www.geeksforgeeks.org/problems/implementing-dijkstra-set-1-adjacency-matrix/1
    /*Given an undirected, weighted graph with V vertices numbered from 0 to V-1 and E edges,
     represented by 2d array edges[][], where edges[i]=[u, v, w] represents the edge
     between the nodes u and v having w edge weight.
    You have to find the shortest distance of all the vertices from the source vertex src,
    and return an array of integers where the ith element denotes the shortest distance
    between ith node and source vertex src.

    Note: The Graph is connected and doesn't contain any negative weight edge.*/

    public int[] dijkstra(int V, int[][] edges, int src) {
        ArrayList<ArrayList<Integer[]>> graph = buildUnDirectedGraph(V,edges);
        //distance,node
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]));
        int[] distance = new int[V];
        Arrays.fill(distance,Integer.MAX_VALUE);
        distance[src]=0;
        pq.add(new int[]{0,src});

        while(!pq.isEmpty()){
            int[] entry=pq.remove();
            int dist= entry[0];
            int node = entry[1];
            for(Integer[] neighbour:graph.get(node)){
                int neighbourNode=neighbour[0];
                int edgeWt = neighbour[1];
                int newDist = distance[node]+edgeWt;
                if(newDist<distance[neighbourNode]){
                    distance[neighbourNode]=newDist;
                    pq.add(new int[]{newDist,neighbourNode});
                }
            }
        }
        for(int i =0;i<distance.length;i++){
            if(distance[i]==Integer.MAX_VALUE)distance[i]=-1;
        }
        return distance;
    }

    public ArrayList<ArrayList<Integer[]>> buildUnDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer[]>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(new Integer[]{v,edge[2]});
            adjList.get(v).add(new Integer[]{u,edge[2]});
        }
        return adjList;
    }
}
