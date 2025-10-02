package Graphs.ShortestPath_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class NumberOfWayToArriveAtDestination_10 {
    //https://leetcode.com/problems/number-of-ways-to-arrive-at-destination/description/
    /*You are in a city that consists of n intersections numbered from 0 to n - 1 with
    bi-directional roads between some intersections.
    The inputs are generated such that you can reach any intersection from any other intersection and
    that there is at most one road between any two intersections.
    You are given an integer n and a 2D integer array roads where roads[i] = [ui, vi, timei] means
    that there is a road between intersections ui and vi that takes timei minutes to travel.
    You want to know in how many ways you can travel from intersection 0 to intersection n - 1 in the
    shortest amount of time.
    Return the number of ways you can arrive at your destination in the shortest amount of time.
     Since the answer may be large, return it modulo 109 + 7.*/

    public int countPaths(int n, int[][] roads) {
        ArrayList<ArrayList<Integer[]>> graph=buildUnDirectedGraph(n,roads);
        PriorityQueue<long[]> pq=new PriorityQueue<>(Comparator.comparingLong((long[] o) -> o[0]));
        long[] distance=new long[n];
        int[] way=new int[n];

        int mod = 1000000000+7;
        Arrays.fill(distance,Long.MAX_VALUE);
        distance[0]=0;
        Arrays.fill(way,0);
        way[0]=1;//src can be reached in 1 way only
        pq.add(new long[]{0,0});

        while(!pq.isEmpty()){
            long[] entry=pq.remove();
            long dist= entry[0];
            int node = (int) entry[1];
            for(Integer[] neighbour:graph.get(node)){
                int neighbourNode=neighbour[0];
                int edgeWt = neighbour[1];
                long newDist = dist+edgeWt;
                if(newDist<distance[neighbourNode]){
                    distance[neighbourNode]=newDist;
                    way[neighbourNode]=way[node];
                    pq.add(new long[]{newDist,neighbourNode});
                } else if(newDist==distance[neighbourNode]){
                    //already visited with same distance
                    way[neighbourNode]=(way[neighbourNode]+way[node])%mod;
                }
            }
        }
        return way[n-1];

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
