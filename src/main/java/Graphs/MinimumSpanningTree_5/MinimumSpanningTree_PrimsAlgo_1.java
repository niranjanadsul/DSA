package Graphs.MinimumSpanningTree_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumSpanningTree_PrimsAlgo_1 {
    //https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1
    /*Given a weighted, undirected, and connected graph with V vertices and E edges,
    your task is to find the sum of the weights of the edges in the
    Minimum Spanning Tree (MST) of the graph.
    The graph is provided as a list of edges, where each edge is represented as [u, v, w],
    indicating an edge between vertex u and vertex v with edge weight w.*/
    public int spanningTree(int V, int[][] edges) {
        ArrayList<ArrayList<Integer[]>> graph=buildUnDirectedGraph(V,edges);
        boolean[] visited = new boolean[V];
        Arrays.fill(visited,false);
        ArrayList<int[]> mstEdges=new ArrayList<>();
        int mstSum=0;
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] a)->a[0]));
        pq.add(new int[]{0,-1,0});
        while(!pq.isEmpty()){
            int[] val=pq.remove();
            int wt=val[0];
            int u=val[1];
            int v=val[2];
            if(!visited[v]){
                visited[v]=true;
                mstSum+=wt;
                if(u!=-1)
                    mstEdges.add(new int[]{u,v});
                for(Integer[] neighbour:graph.get(v)){
                    if(!visited[neighbour[0]]){
                        pq.add(new int[]{neighbour[1],v,neighbour[0]});
                    }
                }
            }
        }
        return mstSum;
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

    public static void main(String[] args) {
        MinimumSpanningTree_PrimsAlgo_1 minimumSpanningTreePrimsAlgo1=new MinimumSpanningTree_PrimsAlgo_1();
        minimumSpanningTreePrimsAlgo1.spanningTree(3,new int[][]{
                {0, 1, 5},
                {1, 2, 3},
                {0, 2, 1}
        });
    }
}
