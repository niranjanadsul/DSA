package Graphs.MinimumSpanningTree_5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.stream.Collectors;

public class MakeNetworkConnected_4 {
    //https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
    /*There are n computers numbered from 0 to n - 1 connected by ethernet cables connections
    forming a network where connections[i] = [ai, bi] represents a connection between computers ai and bi.
    Any computer can reach any other computer directly or indirectly through the network.
    You are given an initial computer network connections.
    You can extract certain cables between two directly connected computers,
    and place them between any pair of disconnected computers to make them directly connected.

    Return the minimum number of times you need to do this in order to make all the computers connected.
    If it is not possible, return -1.*/

    //how to relate this problem to graph
    //the input contains n and edges array same as graph
    //the network contains isolated nodes
    //to make the network connected we will need to connect the isolated nodes
    //number operations will be same as number of isolated nodes
    //how to find the isolated nodes
    //we can find number of group/ components in a graph using disjoint set
    //before this we need to find the extra edges so that we can identify if it is possible to
    //make network connected. As the extra edges should be equal or more than isolated nodes
    //the edge that creates cycle in disjoint set operations will be extra edges

    //TC= iterating the edges * performing disjoint set operations on each edge
    //    = O(E * (4*alpha))
    public int makeConnected(int V, int[][] connections) {
        int[] par=new int[V];
        for(int i=0;i<V;i++)
            par[i]=i;
        int[] size=new int[V];
        Arrays.fill(size,1);

        int extraEdge=0;
        for(int[] edge:connections){
            if(unionSet(par,size,edge[0],edge[1]))
                extraEdge++;
        }

        //just run the DSU again to make sure the parent are path compressed
        for(int[] edge:connections){
            unionSet(par,size,edge[0],edge[1]);
        }
        //count number of components
        HashSet<Integer> components=Arrays.stream(par)
                                        .boxed() // Convert int to Integer
                                        .collect(Collectors.toCollection(HashSet::new));
        if(components.size()==1)
            return 0;
        if(extraEdge<components.size()-1)
            return -1;
        return components.size()-1;
    }

    public int find(int par[], int x) {
        if(par[x]==x)
            return x;
        return par[x]=find(par,par[x]);//path compression
    }

    public boolean unionSet(int par[], int[] size, int x, int z) {
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
        MakeNetworkConnected_4 makeNetwrokConnected4=new MakeNetworkConnected_4();
        makeNetwrokConnected4.makeConnected(5,new int[][]{
                {0,1},{0,2},{3,4},{2,3}
        });
    }
}
