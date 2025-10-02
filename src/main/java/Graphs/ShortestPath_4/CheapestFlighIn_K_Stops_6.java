package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CheapestFlighIn_K_Stops_6 {
    //https://leetcode.com/problems/cheapest-flights-within-k-stops/description/
    /*There are n cities connected by some number of flights.
    You are given an array flights where flights[i] = [fromi, toi, pricei] indicates
    that there is a flight from city from i to city to i with cost pricei.

    You are also given three integers src, dst, and k,
    return the cheapest price from src to dst with at most k stops.
    If there is no such route, return -1.*/

    //TC=O((V+E)*log V)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        ArrayList<ArrayList<Integer[]>> graph = buildDirectedGraph(n,flights);

        //cost,stop,node
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] a)->a[1]));
        int[] cost = new int[n];
        Arrays.fill(cost,Integer.MAX_VALUE);
        cost[src]=0;
        pq.add(new int[]{0,0,src});

        while(!pq.isEmpty()){
            int[] entry=pq.remove();
            int costToNode=entry[0];
            int stops= entry[1];
            int node = entry[2];
            if(stops>k)
                continue;//as the stops limit is exhausted, no need to explore neighbours of this node
            for(Integer[] neighbour:graph.get(node)){
                int neighbourNode=neighbour[0];
                int edgeWt = neighbour[1];
                int newCost = costToNode+edgeWt;
                if(newCost<cost[neighbourNode]){
                    cost[neighbourNode]=newCost;
                    pq.add(new int[]{newCost,stops+1,neighbourNode});
                }
            }
        }
        if(cost[dst]==Integer.MAX_VALUE)cost[dst]=-1;
        return cost[dst];
    }

    public ArrayList<ArrayList<Integer[]>> buildDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer[]>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            adjList.get(u).add(new Integer[]{v,edge[2]});
        }
        return adjList;
    }
}
