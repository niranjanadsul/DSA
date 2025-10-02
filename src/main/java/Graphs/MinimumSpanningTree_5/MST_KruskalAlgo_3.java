package Graphs.MinimumSpanningTree_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MST_KruskalAlgo_3 {
    //https://www.geeksforgeeks.org/problems/minimum-spanning-tree-kruskals-algorithm/1
    /*Given a weighted, undirected, and connected graph with V vertices and E edges,
    the task is to find the sum of the weights of the edges in the Minimum Spanning Tree (MST)
    of the graph using Kruskal's Algorithm. The graph is represented as an edge list edges[][],
    where edges[i] = [u, v, w] denotes an undirected edge between u and v with weight w.*/

    //TC=O(E * log E)
    static int kruskalsMST(int V, int[][] edges) {
        int[] par=new int[V];
        for(int i=0;i<V;i++)
            par[i]=i;
        int[] size=new int[V];
        Arrays.fill(size,1);

        PriorityQueue<int[]> pq=new PriorityQueue<>(Comparator.comparingInt((int[] a)->a[2]));
        for(int[] edge:edges)
            pq.add(edge);
        int mstSum=0;
        ArrayList<int[]> mstEdges=new ArrayList<>();
        while (!pq.isEmpty()){
            int[] val = pq.remove();
            int u=val[0];
            int v=val[1];
            int wt=val[2];

            if(!unionSet(par,size,u,v)) {
                mstSum += wt;
                mstEdges.add(new int[]{u,v});
            }
        }
        return mstSum;
    }

    static int find(int par[], int x) {
        if(par[x]==x)
            return x;
        return par[x]=find(par,par[x]);//path compression
    }

    static boolean unionSet(int par[], int[] size, int x, int z) {
        //union by size
        int UPu=find(par,x);
        int UPv=find(par,z);
        if(UPu==UPv)
            return true;
        if(size[UPu]<size[UPv]){
            par[UPu]=UPv;
            size[UPv]+=size[UPu];
        }else{
            par[UPv]=UPu;
            size[UPu]+=size[UPv];
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(MST_KruskalAlgo_3.kruskalsMST(3,new int[][]{
                {0, 1, 5}, {1, 2, 3}, {0, 2, 1}
        }));
    }
}
