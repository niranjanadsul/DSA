package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NetworkDelayTime_9 {
    //https://leetcode.com/problems/network-delay-time/description/
    /*
    * You are given a network of n nodes, labeled from 1 to n.
    * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
    * where ui is the source node, vi is the target node,
    * and wi is the time it takes for a signal to travel from source to target.

    We will send a signal from a given node k.
    * Return the minimum time it takes for all the n nodes to receive the signal.
    * If it is impossible for all the n nodes to receive the signal, return -1.*/

    //this problem can be related to finding shortest Path from src node to all nodes
    // as we need to send signal from k (src node) and the signal needs to reach all other nodes.
    //but the minimum time to reach ALL nodes will be the maximum shortest distance
    //as the shortest distance to the farthest node will decide the final answer as the signal will reach
    //that node at the last

    //TC=O((n + times.length)* log n)
    public int networkDelayTime(int[][] times, int n, int k) {
        ArrayList<ArrayList<Integer[]>> graph = buildDirectedGraph(n,times);
        int src = k-1;
        //distance,node
        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] o) -> o[0]));
        int[] distance = new int[n];
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
        int max=Integer.MIN_VALUE;
        for(int i =0;i<distance.length;i++){
            if(distance[i]==Integer.MAX_VALUE)
                return -1;
            else
                max=Math.max(max,distance[i]);
        }
        return max;
    }

    public ArrayList<ArrayList<Integer[]>> buildDirectedGraph(int nodes, int[][] edges){
        ArrayList<ArrayList<Integer[]>> adjList = new ArrayList<>();
        for(int i =0;i<nodes;i++){
            adjList.add(i,new ArrayList<>());
        }
        for(int[] edge:edges){
            int u=edge[0]-1;
            int v=edge[1]-1;
            adjList.get(u).add(new Integer[]{v,edge[2]});
        }
        return adjList;
    }

}
