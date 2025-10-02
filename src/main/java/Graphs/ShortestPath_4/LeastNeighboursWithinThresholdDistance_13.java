package Graphs.ShortestPath_4;

import java.util.Arrays;

public class LeastNeighboursWithinThresholdDistance_13 {
    //https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
    /*There are n cities numbered from 0 to n-1.
    Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and
    weighted edge between cities fromi and toi, and given the integer distanceThreshold.

    Return the city with the smallest number of cities that are reachable through some path and
    whose distance is at most distanceThreshold, If there are multiple such cities,
    return the city with the greatest number.

    Notice that the distance of a path connecting cities i and
    j is equal to the sum of the edges' weights along that path.

    Example 1:
    Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
    Output: 3
    Explanation: The figure above describes the graph.
    The neighboring cities at a distanceThreshold = 4 for each city are:
    City 0 -> [City 1, City 2]
    City 1 -> [City 0, City 2, City 3]
    City 2 -> [City 0, City 1, City 3]
    City 3 -> [City 1, City 2]
    Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4,
     but we have to return city 3 since it has the greatest number.

    Example 2:
    Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
    Output: 0
    Explanation: The figure above describes the graph.
    The neighboring cities at a distanceThreshold = 2 for each city are:
    City 0 -> [City 1]
    City 1 -> [City 0, City 4]
    City 2 -> [City 3, City 4]
    City 3 -> [City 2, City 4]
    City 4 -> [City 1, City 2, City 3]
    The city 0 has 1 neighboring city at a distanceThreshold = 2.*/
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int max = 100000000;
        int[][] dist=new int[n][n];
        for(int[] adj:dist)
            Arrays.fill(adj,max);
        for(int[] edge:edges){
            int u=edge[0];
            int v=edge[1];
            int wt=edge[2];
            dist[u][v]=wt;
            dist[v][u]=wt;
        }

        for(int node=0;node<dist.length;node++){
            for(int i=0;i<dist.length;i++){
                for(int j=0;j<dist.length;j++){
                    if(dist[i][node]!=max && max!=dist[node][j])
                        dist[i][j]=Math.min(dist[i][j],dist[i][node]+dist[node][j]);
                }
            }
        }

        int city=-1;
        int minCount=Integer.MAX_VALUE;
        for(int i=0;i<dist.length;i++) {
            int count=0;
            for (int j = 0; j < dist.length; j++) {
                if(i!=j && dist[i][j]<=distanceThreshold){
                    count++;
                }
            }
            if(count<=minCount){
                minCount=count;
                city=i;
            }
        }
        return city;
    }

    public static void main(String[] args) {
        LeastNeighboursWithinThresholdDistance_13 leastNeighboursWithinThresholdDistance13=new LeastNeighboursWithinThresholdDistance_13();
        leastNeighboursWithinThresholdDistance13.findTheCity(4, new int[][]{
                {0,1,3},{1,2,1},{1,3,4},{2,3,1}
        },4);
    }
}
