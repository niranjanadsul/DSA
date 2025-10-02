package Graphs.ShortestPath_4;

import java.util.Arrays;

public class CheapestFlighIn_K_Stops_Optimized_8 {
    //it is the same problem as earlier
    //but as we want the path with atmost k stops. this fits the requirement of BellmanFord algo
    //we will relax the edges for K times
    //if we find the cost ofr dest then return it or return -1

    //TC = O(E.K)  as we relax all edges for K times only
    public static int max = Integer.MAX_VALUE;
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k)  {
        int[] distance=new int[n];
        Arrays.fill(distance,max);
        distance[src]=0;
        for(int i=1;i<=k;i++){
            relaxEdge(flights,distance);
        }
        if(distance[dst]==max)
            return -1;
        return distance[dst];
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
